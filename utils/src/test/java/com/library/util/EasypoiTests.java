package com.library.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.library.utils.poi.ExcelUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * easypoi 测试类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-day01-18
 */
public class EasypoiTests {

    @Test
    public void mapExport() throws IOException {
        //模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("路飞","1",new Date());
        Person person2 = new Person("娜美","2", DateUtils.addDays(new Date(),3));
        Person person3 = new Person("索隆","1", DateUtils.addDays(new Date(),10));
        Person person4 = new Person("小狸猫","1", DateUtils.addDays(new Date(),-10));
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(new HashMap(){{
            put("title", new ExportParams("花名册","草帽一伙"));
            put("entity", Person.class);
            put("data", personList);
        }});
        list.add(new HashMap(){{
            put("title", new ExportParams("花名册1","草帽一伙1"));
            put("entity", Person.class);
            put("data", personList);
        }});


//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("花名册","草帽一伙"), Person.class, list);
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        File file = new File("E:/workbook10.xls");
        InputStream in = new ByteArrayInputStream(out.toByteArray());
        file.createNewFile();
        FileUtils.copyInputStreamToFile(in, file);
        out.close();
    }
}
