package com.github.bpazy.poibrary.export;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Collection;

/**
 * @author ziyuan
 */
public class ExportContext {

    public Workbook createExcel(ExportParam param, Class<?> type, Collection<?> dataSet) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        sheet.createRow(0);
        return null;
    }
}
