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

package com.library.utils.itext.constant;

/**
 * PDF 工具常量.
 *
 * @author liufeifei02@beyondsoft.com
 * @version 1.0
 * @date 2019-01-11
 */
public interface PdfConstant {

    /**
     * 作者、标题
     */
    String AUTHOR = "beyondSoft";
    String SUBJECT = "document";
    String TITLE = "beyondSoft document";

    /**
     * 中文字体支持
     */
    String BASE_FONT_NAME = "STSongStd-Light";
    String BASE_FONT_ENCODING = "UniGB-UCS2-H";

    /**
     * 边距值
     */
    float PAGE_MARGIN_LEFT = 10f;
    float PAGE_MARGIN_RIGHT = 10f;
    float PAGE_MARGIN_TOP = 10f;
    float PAGE_MARGIN_BOTTOM = 10f;

    /**
     * 水印
     */
    String WATER_MARK_TEXT = "beyondSoft";
    float WATER_MARK_FONT_SIZE = 30f;
    float WATER_MARK_OPACITY = 0.2f;
    float WATER_MARK_MATRIX_X = 60;
    float WATER_MARK_MATRIX_Y = 90;
    float WATER_MARK_ALIGNED_X = 200f;
    float WATER_MARK_ALIGNED_Y = 300f;
    float WATER_MARK_ROTATION = 45f;

    /**
     * 页眉、页脚
     */
    float H_F_FONT_SIZE = 10f;
    String HEADER_TEXT = "测试文档";
    float HEADER_MARGIN_TOP = -15f;
    float HEADER_ROTATION = 0f;
    String FOOTER_TEXT = "页码：";
    float FOOTER_MARGIN_BOTTOM = -15f;
    float FOOTER_MARGIN_ROTATION = 0f;

    /**
     * Style Date
     */
    String STYLE_DATE_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * 默认边框颜色
     */
    int BORDER_DEFAULT_RED = 90;
    int BORDER_DEFAULT_GREEN = 140;
    int BORDER_DEFAULT_BLUE = 200;

    /**
     * 默认背景颜色
     */
    int BG_DEFAULT_RED = 80;
    int BG_DEFAULT_GREEN = 130;
    int BG_DEFAULT_BLUE = 180;

}
