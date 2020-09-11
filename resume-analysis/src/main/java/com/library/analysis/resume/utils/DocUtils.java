package com.library.analysis.resume.utils;

import com.library.analysis.resume.model.Basic;
import com.library.analysis.resume.model.Classification;
import com.library.analysis.resume.model.ClassificationEnum;
import com.library.analysis.resume.model.Resume;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * .
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2020-09-10
 */
public class DocUtils {

    /**
     * 获取Doc文本内容集合
     *
     * @param filePath String
     * @return List<String>
     * @throws IOException
     */
//    public static List<String> getTexts(String filePath) throws IOException {
//        InputStream is = new FileInputStream(filePath);
//        XWPFDocument document = new XWPFDocument(is);
//        List<String> texts = new ArrayList<>();
//        // 文本提取
//        XWPFWordExtractor we = new XWPFWordExtractor(document);
//        String text = we.getText();
//        final String[] split = text.split("\n");
//        Arrays.stream(split).forEach(str -> {
//            if (StringUtils.isNotBlank(str.trim())) {
//                texts.add(str.trim());
//            }
//        });
//        // 图片提取
//        getPictures(document);
//        return texts;
//    }
    public static List<String> getTexts(String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        List<String> texts = new ArrayList<>();
        POIXMLTextExtractor we = null;
        String text = "";
        // 文本提取
        if (filePath.endsWith(".docx")) {
            XWPFDocument document = new XWPFDocument(is);
            we = new XWPFWordExtractor(document);
            text = we.getText();
        } else if (filePath.endsWith(".doc")) {
            HWPFDocument document = new HWPFDocument(is);
            text = document.getDocumentText();
        }

        String[] split = new String[0];
        if (text.indexOf("\r") == -1 && text.indexOf("\r\n") == -1) {
            split = text.split("\n");
        } else if (text.indexOf("\n") == -1 && text.indexOf("\r\n") == -1) {
            split = text.split("\r");
        }
        Arrays.stream(split).forEach(str -> {
            if (StringUtils.isNotBlank(str.trim())) {
                texts.add(str.trim());
            }
        });
        // 图片提取
//        getPictures(document);
        return texts;
    }

    public static void getPictures(XWPFDocument document) {
        List<XWPFPictureData> allPictures = document.getAllPictures();
        String dir = "E:\\beyondsoft\\简历\\";
        allPictures.forEach(pic -> {
            try (FileOutputStream out = new FileOutputStream(dir + pic.getFileName())) {
                out.write(pic.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 获取简历六大通用分类
     *
     * @param filePath String
     * @return Classification
     */
    public static Classification getClassification(String filePath) {
        try {
            Classification classification = new Classification();
            List<String> texts = getTexts(filePath);
            Map<ClassificationEnum, Integer> indexMap = getIndex(texts);
            Map<ClassificationEnum, int[]> indexRangeMap = getIndexRange(texts);
            RegexUtils.REGEX.keySet().forEach(key -> {
                int[] range = indexRangeMap.get(key);
                if (Objects.nonNull(range)) {
                    List<String> rangeTexts = texts.subList(range[0], range[1]);
                    switch (key) {
                        case works:
                            classification.setWorks(rangeTexts);
                            break;
                        case basics:
                            classification.setBasics(rangeTexts);
                            break;
                        case others:
                            classification.setOthers(rangeTexts);
                            break;
                        case educations:
                            classification.setEducations(rangeTexts);
                            break;
                        case intentions:
                            classification.setIntentions(rangeTexts);
                            break;
                        case evaluations:
                            classification.setEvaluations(rangeTexts);
                            break;
                        case pms:
                            classification.setPms(rangeTexts);
                            break;
                        case skills:
                            classification.setSkills(rangeTexts);
                            break;
                        default:
                            break;
                    }

                }
            });
            // 如果不是从第一行开始 & 不包含个人信息，则默认第一行到最近行为`个人信息`
            if (!indexMap.values().contains(Integer.valueOf(0)) && null == classification.getBasics()) {
                Optional<Integer> first = indexMap.values().stream().sorted().findFirst();
                classification.setBasics(texts.subList(0, first.get()));
            }
            return classification;
        } catch (IOException e) {
            e.printStackTrace();
            // TODO log
        }
        return null;
    }

    /**
     * 获取简历对象
     * @param filePath
     * @return
     */
    public static Resume getResume(String filePath) {
        Classification classification = getClassification(filePath);
        // 个人信息
        List<String> basics = classification.getBasics();

        return null;
    }

    /**
     * 获取个人信息
     * @param basics
     * @return
     */
    private static Basic getBasic(List<String> basics) {
        Basic basic = null;
        if (!CollectionUtils.isEmpty(basics)) {
            basic = new Basic();
            basics.forEach(item -> {

            });

        }
        return basic;
    }

    /**
     * 获取通用分类下标
     *
     * @param texts List<String>
     * @return Map<ClassificationEnum, Integer>
     */
    public static Map<ClassificationEnum, Integer> getIndex(List<String> texts) {
        Map<ClassificationEnum, Integer> indexMap = new HashMap<>(6);
        Stream.iterate(0, i -> i + 1).limit(texts.size()).forEach(i -> {
            RegexUtils.REGEX.entrySet().forEach(entry -> {
                if (Pattern.matches(entry.getValue(), texts.get(i))) {
                    indexMap.put(entry.getKey(), i);
                    return;
                }
            });
        });
        return indexMap;
    }

    /**
     * 获取通用分类下标
     *
     * @param texts List<String>
     * @return Map<ClassificationEnum, Integer>
     */
    public static Map<Integer, ClassificationEnum> getIndex2(List<String> texts) {
        Map<Integer, ClassificationEnum> indexMap = new HashMap<>(6);
        Stream.iterate(0, i -> i + 1).limit(texts.size()).forEach(i -> {
            RegexUtils.REGEX.entrySet().forEach(entry -> {
                if (Pattern.matches(entry.getValue(), texts.get(i))) {
                    indexMap.put(i, entry.getKey());
                    return;
                }
            });
        });
        return indexMap;
    }

    /**
     * @param texts
     * @return
     */
    public static Map<ClassificationEnum, int[]> getIndexRange(List<String> texts) {
        Map<Integer, ClassificationEnum> indexMap2 = getIndex2(texts);
        Map<ClassificationEnum, int[]> indexRangeMap = new HashMap<>(6);
        List<Integer> indexes = indexMap2.keySet().stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < indexes.size() - 1; i++) {
            Integer index = indexes.get(i);
            Integer nextIndex = indexes.get(i + 1);
            int[] range = new int[]{index, nextIndex};
            indexRangeMap.put(indexMap2.get(index), range);
        }
        // last
        Integer lastIndex = indexes.get(indexes.size() - 1);
        indexRangeMap.put(indexMap2.get(lastIndex), new int[]{lastIndex, texts.size()});
        return indexRangeMap;

    }

    public static void main(String[] args) throws Exception {
        final String DOC_PATH0 = "E:\\beyondsoft\\简历\\【JAVA高级工程师_北京】李晓明+5年.docx";
        final String DOC_PATH1 = "E:\\beyondsoft\\简历\\【JAVA高级工程师_北京】何飞 5年.doc";
        getClassification(DOC_PATH1);
    }
}