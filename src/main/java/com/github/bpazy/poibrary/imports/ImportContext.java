package com.github.bpazy.poibrary.imports;

import com.github.bpazy.poibrary.Title;
import com.github.bpazy.reflections.Reflections;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ziyuan
 */
public class ImportContext {
    private Map<Integer, String> titleMap;
    @Getter
    private List<Object> resultList = Lists.newArrayList();

    @SneakyThrows
    public void importExcel(InputStream inputStream, Class target, ImportParams params) {
        Map<String, Field> titleFieldMap = getTitleFieldMap(target);

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        for (Sheet rows : wb) {
            XSSFSheet sheet = (XSSFSheet) rows;
            Iterator<Row> rowIterator = sheet.iterator();

            // 跳过sheet的标题
            for (int i = 0; i < params.getTitleRows(); i++) {
                rowIterator.next();
            }
            // 跳过sheet的头部
            for (int i = 0; i < params.getHeadRows(); i++) {
                Row titleRow = rowIterator.next();
                if (i == 0) {
                    titleMap = getTitleMap(titleRow);
                }
            }

            while (rowIterator.hasNext()) {
                XSSFRow row = (XSSFRow) rowIterator.next();
                int cellIndex = 0;
                Object targetInstance = target.newInstance();
                for (Cell cell : row) {
                    String cellValue = cell.getStringCellValue();
                    Field titleField = titleFieldMap.get(titleMap.get(cellIndex));

                    // 忽略未设置title的值
                    if (titleField != null) {
                        Reflections.setField(targetInstance, titleField.getName(), cellValue);
                    }

                    cellIndex++;
                }
                resultList.add(targetInstance);
                System.out.println();
            }
        }
    }

    private Map<String, Field> getTitleFieldMap(Class target) {
        Map<String, Field> titleFieldMap = Maps.newHashMap();
        Set<Field> allFields = Reflections.getAllFields(target, Title.class);
        for (Field field : allFields) {
            titleFieldMap.put(field.getName(), field);
        }

        return titleFieldMap;
    }

    private Map<Integer, String> getTitleMap(Row titleRow) {
        Map<Integer, String> titleMap = Maps.newHashMap();
        Iterator<Cell> titleRowIterator = titleRow.iterator();
        int i = 0;
        while (titleRowIterator.hasNext()) {
            titleMap.put(i, titleRowIterator.next().getStringCellValue());
            i++;
        }
        return titleMap;
    }
}
