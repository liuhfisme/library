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

package com.library.utils.poi;

import com.library.utils.poi.constant.ExcelConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Excel 工具类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-day01-09
 */
@Slf4j
public class ExcelUtil {

    private ExcelUtil(){
        throw new IllegalArgumentException("ExcelUtil class should not be instanced.");
    }

    /**
     * 创建 excel 字节流
     *
     * @param sheetName sheet 名称
     * @param titles 标题数组
     * @param data 数据数组集合
     * @return ByteArrayOutputStream
     */
    public static ByteArrayOutputStream build(String sheetName, String[] titles, List<Object[]> data) {
        // 声明初始化 HSSFWorkbook 对象(try with resource 写法)
        try(HSSFWorkbook wb = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            // 创建一个表单对象
            Sheet sheet = createSheet(wb, sheetName);
            // 声明一个行
            Row row;
            if (ArrayUtils.isNotEmpty(titles)&&CollectionUtils.isNotEmpty(data)) {
                // 创建一个日期类型格式(用于日期类型的格式化)
                CellStyle dateStyle = buildStyle(wb);
                row = crateRow(sheet, ExcelConstant.SHEET_TITLE_INDEX);
                // 遍历标题写入单元格
                int titlesLength = titles.length;
                for (int i = 0; i < titlesLength; i++) {
                    createCell(row, i, titles[i], dateStyle);
                }
                // 遍历数据写入单元格
                int dataLength = data.size();
                for (int i = 0; i < dataLength ; i++) {
                    row = crateRow(sheet, i+1);
                    int dataRowLength = data.get(i).length;
                    for (int j = 0; j < dataRowLength; j++) {
                        createCell(row, j, data.get(i)[j], dateStyle);
                    }
                }
            }
            wb.write(out);
            return out;
        } catch (IOException e) {
            log.error("stream write error!", e);
        }
        return null;
    }

    /**
     * 创建一个Excel表单
     * @param wb Workbook对象
     * @param sheetName 表单名称
     * @return Sheet
     */
    private static Sheet createSheet(Workbook wb, String sheetName) {
        return wb.createSheet(StringUtils.isEmpty(sheetName) ? ExcelConstant.SHEET_DEFAULT_NAME : sheetName);
    }

    /**
     * 创建一个Excel表单行
     * @param sheet sheet对象
     * @param index 下标
     * @return Row
     */
    private static Row crateRow(Sheet sheet, int index) {
        return sheet.createRow(index);
    }

    /**
     * 创建一个Excel表单单元格
     * @param row row对象
     * @param index 下标
     * @param cellValue 表格值对象
     * @param dateStyle 日期类型样式
     */
    private static void createCell(Row row, int index, Object cellValue, CellStyle dateStyle) {
        Cell cell = row.createCell(index);
        if (cellValue != null) {
            if (cellValue instanceof Double) {
                cell.setCellValue((Double) cellValue);
                cell.setCellType(CellType.NUMERIC);
            } else if (cellValue instanceof Boolean) {
                cell.setCellValue((Boolean) cellValue);
                cell.setCellType(CellType.BOOLEAN);
            } else if (cellValue instanceof Date) {
                cell.setCellValue((Date) cellValue);
                cell.setCellStyle(dateStyle);
            } else if (cellValue instanceof Calendar) {
                cell.setCellValue((Calendar) cellValue);
            } else {
                cell.setCellValue(cellValue.toString());
            }
        } else {
            cell.setCellType(CellType.BLANK);
        }
    }

    /**
     * 创建 Cell 样式对象(暂时只做Date)
     *
     * @param wb HSSFWorkbook 对象
     * @return CellStyle
     */
    private static CellStyle buildStyle(HSSFWorkbook wb) {
        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFDataFormat dateFormat= wb.createDataFormat();
        cellStyle.setDataFormat(dateFormat.getFormat(ExcelConstant.STYLE_DATE_FORMAT));
        return cellStyle;
    }

}
