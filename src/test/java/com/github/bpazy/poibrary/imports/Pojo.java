package com.github.bpazy.poibrary.imports;

import com.github.bpazy.poibrary.Title;
import lombok.Data;

/**
 * @author ziyuan
 */
@Data
public class Pojo {
    @Title("var1")
    private String var1;
    @Title("var2")
    private String var2;
    private String var3;
}
