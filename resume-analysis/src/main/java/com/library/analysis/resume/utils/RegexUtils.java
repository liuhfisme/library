package com.library.analysis.resume.utils;

import com.library.analysis.resume.model.BasicEnum;
import com.library.analysis.resume.model.ClassificationEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 正则匹配工具类.
 *
 * @date 2020-09-10
 * @version 1.0
 * @author liufefei02@beyondsoft.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexUtils {
    /**
        1、教育背景相关关键字正则表达式内容为:
        “教\s*育\s*背\s*景|教\s*育\s*经\s*历|学\s*习\s*经\s*历|求\s*学\s*经\s*历|学\s*习\s*经\s*历|教\s*育\s*概\s*况|教\s*育|学\s*历\s*教\s*育”
        2、工作经历相关关键字正则表达式内容为:
        “工\s*作\s*经\s*验|工\s*作\s*经\s*历|工\s*作|工\s*作\s*历\s*史|工\s*作\s*背\s*景|工\s*作\s*简\s*介|工\s*作\s*能\s*力”
        3、求职意向
        ”求\s*职\s*意\s*向|应\s*聘\s*岗\s*位|求\s*职\s*目\s*标“
        4、个人信息
        ”基\s*本\s*信\s*息|个\s*人\s*信\s*息“
        5、自我评价
        ”自\s*我\s*评\s*价“
        6、专业技能
        ”专\s*业\s*技\s*能|职\s*业\s*技\s*能|技\s*能\s*描\s*述|技\s*术\s*能\s*力|职\s*业\s*技\s*能|技\s*能|技\s*术\s*能\s*力|技\s*术\s*背\s*景|技\s*能\s*背\s*景“
        6、项目经历
        ”项\s*目\s*经\s*历|项\s*目\s*经\s*验|项\s*目\s*历\s*史|项\s*目\s*背\s*景|项\s*目\s*简\s*介|项\s*目\s*能\s*力“
    */

    public final static String REG_EDUCATIONS = "^\\S?\\s*(教\\s*育\\s*背\\s*景|教\\s*育\\s*经\\s*历|学\\s*习\\s*经\\s*历|求\\s*学\\s*经\\s*历|学\\s*习\\s*经\\s*历|教\\s*育\\s*概\\s*况|教\\s*育|学历教育)\\s*\\S?$";
    public final static String REG_WORKS = "^\\S?\\s*(工\\s*作\\s*经\\s*验|工\\s*作\\s*经\\s*历|工\\s*作|工\\s*作\\s*历\\s*史|工\\s*作\\s*背\\s*景|工\\s*作\\s*简\\s*介|工\\s*作\\s*能\\s*力)\\s*\\S?$";
    public final static String REG_INTENTIONS = "^\\S?\\s*(求\\s*职\\s*意\\s*向|应\\s*聘\\s*岗\\s*位|求\\s*职\\s*目\\s*标)\\s*\\S?$";
    public final static String REG_BASICS = "^\\S?\\s*(基\\s*本\\s*信\\s*息|个\\s*人\\s*信\\s*息)\\s*\\S?$";
    public final static String REG_EVALUATIONS = "^\\S?\\s*(自\\s*我\\s*评\\s*价)\\s*\\S?$";
    public final static String REG_SKILLS = "^\\S?\\s*(专\\s*业\\s*技\\s*能|职\\s*业\\s*技\\s*能|技\\s*能\\s*描\\s*述|技\\s*术\\s*能\\s*力|职\\s*业\\s*技\\s*能|技\\s*能|技\\s*术\\s*能\\s*力|技\\s*术\\s*背\\s*景|技\\s*能\\s*背\\s*景)\\s*\\S?$";
    public final static String REG_PMS = "^\\S?\\s*(项\\s*目\\s*经\\s*历|项\\s*目\\s*经\\s*验|项\\s*目\\s*历\\s*史|项\\s*目\\s*背\\s*景|项\\s*目\\s*简\\s*介|项\\s*目\\s*能\\s*力)\\s*\\S?$";

    /**
     * 个人信息
     */
    public final static String REG_BASIC_NAME = "(姓\\s*名|名\\s*字|名\\s*称|昵\\s*称|称\\s*呼)\\s*(:|：)\\s*\\S+";


    public final static Map<ClassificationEnum, String> REGEX = new HashMap<>();
    public final static Map<BasicEnum, String> BASIC_REGEX = new HashMap<>();

    static {
        REGEX.put(ClassificationEnum.educations, REG_EDUCATIONS);
        REGEX.put(ClassificationEnum.works, REG_WORKS);
        REGEX.put(ClassificationEnum.intentions, REG_INTENTIONS);
        REGEX.put(ClassificationEnum.basics, REG_BASICS);
        REGEX.put(ClassificationEnum.evaluations, REG_EVALUATIONS);
        REGEX.put(ClassificationEnum.skills, REG_SKILLS);
        REGEX.put(ClassificationEnum.pms, REG_PMS);
        // 个人信息
        BASIC_REGEX.put(BasicEnum.name, REG_BASIC_NAME);
    }

    public static void main(String[] args) {
        boolean bool = Pattern.matches(REG_EDUCATIONS, "教育背景");
        System.out.println(bool);
    }
}