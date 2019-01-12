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

package com.library.utils.itext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.library.utils.itext.constant.PdfConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * PDF 工具类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-01-11
 */
@Slf4j
public class PdfUtil {
    private PdfUtil() {
        throw new IllegalArgumentException("PdfUtil class should not be instanced.");
    }

    /**
     * 创建 PDF 字节流
     * @param titles 标题数据
     * @param data 表格数据
     * @return ByteArrayOutputStream
     */
    public static ByteArrayOutputStream build(String[] titles, List<Object[]> data) {
        Document doc = null;
        PdfWriter writer = null;
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            // 中文字体，要有itext-asian.jar支持(默认的itext.jar是不支持中文的)
            log.debug("set document chinese font support. ");
            BaseFont font = BaseFont.createFont(PdfConstant.BASE_FONT_NAME, PdfConstant.BASE_FONT_ENCODING, BaseFont.NOT_EMBEDDED);
            // new Document
            log.debug("set page size a4.[{}]", PageSize.A4);
            Rectangle rectangle = new Rectangle(PageSize.A4);
            doc = new Document(rectangle, PdfConstant.PAGE_MARGIN_LEFT, PdfConstant.PAGE_MARGIN_RIGHT,
                PdfConstant.PAGE_MARGIN_TOP, PdfConstant.PAGE_MARGIN_BOTTOM);
            writer = PdfWriter.getInstance(doc, out);
            // 设置页面水印、页眉和页脚等功能
            SdkPdfPageEvent pageEvent = new SdkPdfPageEvent();
            writer.setPageEvent(pageEvent);
            doc.open();
            // 设置文档说明
            doc.addAuthor(PdfConstant.AUTHOR);
            doc.addSubject(PdfConstant.SUBJECT);
            doc.addTitle(PdfConstant.TITLE);
            BaseColor borderColor = new BaseColor(PdfConstant.BORDER_DEFAULT_RED, PdfConstant.BORDER_DEFAULT_GREEN, PdfConstant.BORDER_DEFAULT_BLUE);
            BaseColor bgColor = new BaseColor(PdfConstant.BG_DEFAULT_RED, PdfConstant.BG_DEFAULT_GREEN, PdfConstant.BG_DEFAULT_BLUE);
            if (ArrayUtils.isNotEmpty(titles)&& CollectionUtils.isNotEmpty(data)) {
                log.debug("each titles and data, start. titles[{}], data[{}]", titles, data);
                int titleLength = titles.length;
                float[] relativeWidths = new float[titleLength];
                for (int i = 0; i < titleLength; i++) {
                    relativeWidths[i] = 1f/titleLength;
                }

                PdfPTable table = new PdfPTable(relativeWidths);
                //遍历标题
                Arrays.stream(titles).forEach(title ->
                    table.addCell(createCell(title, font, borderColor, bgColor)));
                //遍历主数据
                data.forEach(items -> {
                    if (ArrayUtils.isNotEmpty(items)) {
                        // table 没有 创建row的方法，按照标题列数addCell
                        for (int i = 0; i < titleLength; i++) {
                            Object cellValue = null;
                            if (i < items.length) {
                                cellValue = items[i];
                            }
                            table.addCell(createCell(cellValue, 10, font, null, Paragraph.ALIGN_CENTER, borderColor));
                        }
                    }
                });
                doc.add(table);
                log.debug("each titles and data, end. table[{}]", table);
            }
            log.debug("build pdf over.");
            return  out;
        } catch (DocumentException | IOException e) {
            log.error("PdfUtil build error.{}", e);
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
        return null;
    }

    /**
     * 处理表格 Cell 值
     * @param obj 值
     * @return String
     */
    private static String generateCellValue(Object obj) {
        String text = "";
        if (obj != null) {
            if (obj instanceof Date) {
                text = DateFormatUtils.format((Date) obj, PdfConstant.STYLE_DATE_FORMAT);
            } else if (obj instanceof Calendar) {
                text = DateFormatUtils.format((Calendar) obj, PdfConstant.STYLE_DATE_FORMAT);
            } else {
                text = obj.toString();
            }
        }
        return text;
    }

    /**
     * 创建表格 Cell
     * @param text          Cell文字内容
     * @param fontsize      字体大小
     * @param font          字体
     * @param colspan       合并列数量
     * @param align         显示位置(左中右，Paragraph对象)
     * @param borderColor   Cell边框颜色
     * @param bgColor       Cell背景色
     * @return PdfPCell
     */
    private static PdfPCell createCell(Object text, int fontsize, BaseFont font, Integer colspan, Integer align, BaseColor borderColor, BaseColor bgColor) {
        Paragraph paragraph = new Paragraph(generateCellValue(text), new Font(font, fontsize));
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setFixedHeight(20);
        // 上中下，Element对象
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        if (align != null) {
            cell.setHorizontalAlignment(align);
        }
        if (colspan != null && colspan > 1) {
            cell.setColspan(colspan);
        }
        if (borderColor != null) {
            cell.setBorderColor(borderColor);
        }
        if (bgColor != null) {
            cell.setBackgroundColor(bgColor);
        }
        return cell;
    }

    /**
     * 创建表格 Cell
     * @param text          Cell文字内容
     * @param font          字体
     * @param borderColor   Cell边框颜色
     * @return PdfPCell
     */
    private static PdfPCell createCell(Object text, BaseFont font, BaseColor borderColor) {
        return createCell(text, 12, font, null, null, borderColor, null);
    }

    /**
     * 创建表格 Cell
     * @param text          Cell文字内容
     * @param font          字体
     * @param borderColor   Cell边框颜色
     * @param bgColor       Cell背景色
     * @return PdfPCell
     */
    private static PdfPCell createCell(Object text, BaseFont font, BaseColor borderColor, BaseColor bgColor) {
        return createCell(text, 12, font, null, null, borderColor, bgColor);
    }

    /**
     * 创建表格 Cell
     * @param text          Cell文字内容
     * @param fontsize      字体大小
     * @param font          字体
     * @param colspan       合并列数量
     * @param align         显示位置(左中右，Paragraph对象)
     * @param borderColor   Cell边框颜色
     * @return PdfPCell
     */
    private static PdfPCell createCell(Object text, int fontsize, BaseFont font, Integer colspan, Integer align, BaseColor borderColor) {
        return createCell(text, fontsize, font, colspan, align, borderColor, null);
    }

    /**
     * SDK 中 PDF 相关的 PageEvent
     */
    static class SdkPdfPageEvent extends PdfPageEventHelper {
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            try {
                log.debug("set water mark, start.");
                PdfContentByte pcb = writer.getDirectContent();
                pcb.saveState();
                // set onStartPage chinese support
                BaseFont font = BaseFont.createFont(PdfConstant.BASE_FONT_NAME, PdfConstant.BASE_FONT_ENCODING, BaseFont.EMBEDDED);
                pcb.setFontAndSize(font, PdfConstant.WATER_MARK_FONT_SIZE);

                // set opacity
                PdfGState pgs = new PdfGState();
                pgs.setFillOpacity(PdfConstant.WATER_MARK_OPACITY);
                pcb.setGState(pgs);

                log.debug("set water mark text. text[{}]", PdfConstant.WATER_MARK_TEXT);
                pcb.beginText();
                pcb.setTextMatrix(PdfConstant.WATER_MARK_MATRIX_X, PdfConstant.WATER_MARK_MATRIX_Y);
                pcb.showTextAligned(Element.ALIGN_LEFT, PdfConstant.WATER_MARK_TEXT, PdfConstant.WATER_MARK_ALIGNED_X,
                    PdfConstant.WATER_MARK_ALIGNED_Y, PdfConstant.WATER_MARK_ROTATION);
                pcb.endText();
                pcb.restoreState();
                log.debug("set water mark, end.");
            } catch (DocumentException | IOException e) {
                log.error("pageEvent onStartPage, error. {}", e);
            }

        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                log.debug("set page header、footer, start.");
                PdfContentByte pcb = writer.getDirectContent();
                pcb.saveState();
                // set onEndPage chinese support
                BaseFont font = BaseFont.createFont(PdfConstant.BASE_FONT_NAME, PdfConstant.BASE_FONT_ENCODING, BaseFont.EMBEDDED);
                pcb.setFontAndSize(font, PdfConstant.H_F_FONT_SIZE);

                // set header
                pcb.beginText();
                pcb.showTextAligned(PdfContentByte.ALIGN_CENTER, PdfConstant.HEADER_TEXT, document.right(),
                    PdfConstant.HEADER_MARGIN_TOP, PdfConstant.HEADER_ROTATION);

                // set footer
                pcb.showTextAligned(PdfContentByte.ALIGN_CENTER, PdfConstant.FOOTER_TEXT + writer.getCurrentPageNumber(),
                    (document.right() + document.left()) / 2, PdfConstant.FOOTER_MARGIN_BOTTOM, PdfConstant.FOOTER_MARGIN_ROTATION);
                pcb.endText();
                pcb.restoreState();
                pcb.closePath();
                log.debug("set page header、footer, end.");
            } catch (DocumentException | IOException e) {
                log.error("pageEvent onEndPage, error. {}", e);
            }

        }
    }
}
