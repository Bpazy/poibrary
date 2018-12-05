package com.github.bpazy.poibrary.export;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.Collection;

/**
 * @author ziyuan
 */
public class ExportUtil {
    public static Workbook exportExcel(ExportParam param, Class<?> type, Collection<?> dataSet) {
        return new ExportContext().createExcel(param, type, dataSet);
    }
}
