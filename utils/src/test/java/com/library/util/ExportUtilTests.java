/*
 * Copyright (c) 2018. Beyondsoft Corporation.
 * All Rights Reserved.
 *
 * BEYONDSOFT CONFIDENTIALITY
 *
 * The information in this file is confidential and privileged from Beyondsoft Corporation,
 * which is intended only for use solely by Beyondsoft Corporation.
 * Disclosure, copying, distribution, or use of the contents of this file by persons other than Beyondsoft Corporation
 * is strictly prohibited and may violate applicable laws.
 */

package com.library.util;

import com.library.utils.poi.ExcelUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Excel 工具测试类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-01-10
 */
public class ExportUtilTests {
    @Test
    public void excel() throws IOException {
        String sheetName = "测试sheet";
        String[] titles = new String[]{"标题1", "标题2", "标题3", "标题4", "标题5"};
        Object[] data1 = new Object[]{"数据1", true, 3.1415926124567812129D, new Date(System.currentTimeMillis()), null};
        Object[] data2 = new Object[]{"数据2", false, 3.1415926D, new Date(System.currentTimeMillis()), null};
        Object[] data3 = new Object[]{"数据3", true, 1234567890123456789L, new Timestamp(System.currentTimeMillis()), null};
        List<Object[]> data = new ArrayList<>();
        data.add(data1);
        data.add(data2);
        data.add(data3);
        ByteArrayOutputStream out = ExcelUtil.build(sheetName, titles, data);
        File file = new File("E:/workbook9.xls");
        InputStream in = new ByteArrayInputStream(out.toByteArray());
        file.createNewFile();
        FileUtils.copyInputStreamToFile(in, file);
        out.close();
    }
}
