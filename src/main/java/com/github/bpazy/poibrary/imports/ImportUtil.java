package com.github.bpazy.poibrary.imports;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @author ziyuan
 */
@Slf4j
public class ImportUtil {

    public static ImportContext importExcel(InputStream inputStream, Class type, ImportParams params) {
        ImportContext context = new ImportContext();
        context.importExcel(inputStream, type, params);
        return context;
    }
}
