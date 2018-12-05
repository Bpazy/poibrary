package com.github.bpazy.poibrary.imports;

import com.github.bpazy.poibrary.po.Pojo;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

/**
 * @author ziyuan
 */
public class ImportUtilTest {

    @Test
    @SneakyThrows
    public void importExcel() {
        URL testXlsxUrl = Thread.currentThread().getContextClassLoader().getResource("test.xlsx");
        assert testXlsxUrl != null;

        ImportContext context = ImportUtil.importExcel(new FileInputStream(new File(testXlsxUrl.toURI())), Pojo.class, new ImportParams());
        System.out.println(context.getResultList());
    }


    @Test
    @SneakyThrows
    public void exportExcel() {
        String folder = System.getProperty("java.io.tmpdir");
    }
}