package com.github.bpazy.poibrary.export;

import com.github.bpazy.poibrary.po.Pojo;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * @author ziyuan
 */
public class ExportUtilTest {
    private String tmpDir = System.getProperty("java.io.tmpdir");

    @Test
    @SneakyThrows
    public void exportExcel() {
        Workbook workbook = ExportUtil.exportExcel(new ExportParam(), Pojo.class, Lists.newArrayList());
        workbook.write(new FileOutputStream(tmpDir, false));
    }
}
