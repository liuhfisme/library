package com.library.utils.poi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.library.utils.poi.model.DynamicField;
import org.apache.poi.ss.usermodel.Workbook;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 动态列工具类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-12
 */
public class DynamicExcelUtil {
    private static List<DynamicField> dynamicFields;
    private static List<Map<String, Object>> staticObjects;
    private static final String DEFAULT_SHEET_TITLE = "总标题";
    private static final String DEFAULT_SHEET_NAME = "SHEET名称";
    static {
        dynamicFields = new ArrayList<>();
        staticObjects = new ArrayList<>();
        DynamicField tempField;
        Map<String, Object> tempObject;
        for (int i = 0; i < 10; i++) {
            tempField = new DynamicField("title"+i, "标题"+i, "text");
            dynamicFields.add(tempField);
        }
        for (int i = 0; i < 5; i++) {
            tempObject = new HashMap<>();
            for (int j = 0; j < 10; j++) {
                tempObject.put("title"+j, "数据值"+i+"."+j);
            }
            staticObjects.add(tempObject);
        }
    }
    public static byte[] build() {
        return build(dynamicFields, staticObjects);
    }

    public static byte[] build(List<DynamicField> dynamicFields, List<Map<String, Object>> staticObjects, ExportParams exportParams) {
        List<ExcelExportEntity> dynamicCols = new ArrayList<>();
        AtomicReference<ExcelExportEntity> dynamicCol = new AtomicReference<>();
        dynamicFields.stream().forEach(field -> {
            dynamicCol.set(new ExcelExportEntity(field.getShowName(), field.getName()));
            dynamicCols.add(dynamicCol.get());
        });

        Workbook wb = ExcelExportUtil.exportExcel(exportParams, dynamicCols, staticObjects);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            wb.write(out);
            return out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] build(List<DynamicField> dynamicFields, List<Map<String, Object>> staticObjects) {
        ExportParams exportParams = new ExportParams(DEFAULT_SHEET_TITLE, DEFAULT_SHEET_NAME, ExcelType.HSSF);
        return build(dynamicFields, staticObjects, exportParams);
    }
}