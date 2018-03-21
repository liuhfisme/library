package com.mine.library.workjob.baihui.paramname;

/**
 * ClassName: ModuleFieldInitParamEnum
 * Description: 模块字段初始化字段参数名枚举
 * Created by feifei.liu on 2017/11/21 16:37
 **/
public enum ModuleFieldInitParamEnum {
    id("id"),
    name("name"),
    remark("remark"),

    creator_id("cid"),
    creator_name("cname"),
    created_time("ctime"),
    modifier_id("uid"),
    modifier_name("uname"),
    modified_time("utime"),
    owner_id("oid"),
    owner_name("oname"),
    is_deleted("isd"),
    project_id("proid"),
    operation_name("opername"),
    is_lock("isl"),
    approval_status("appros"),
    latest_time("ltime"),
    currency_id("curid"),
    currency_name("curname"),
    exchange_rate("excr"),
    exchange_rate_date("excrd"),

    creatorId("cid"),
    creatorName("cname"),
    createdTime("ctime"),
    modifierId("uid"),
    modifierName("uname"),
    modifiedTime("utime"),
    ownerId("oid"),
    ownerName("oname"),
    isDeleted("isd"),
    projectId("proid"),
    operationName("opername"),
    isLock("isl"),
    approvalStatus("appros"),
    latestTime("ltime"),
    currencyId("curid"),
    currencyName("curname"),
    exchangeRate("excr"),
    exchangeRateDate("excrd");

//    cid("creator_id"),
//    cname("creator_name"),
//    ctime("created_time"),
//    uid("modifier_id"),
//    uname("modifier_name"),
//    utime("modified_time"),
//    oid("owner_id"),
//    oname("owner_name"),
//    isd("is_deleted"),
//    proid("project_id"),
//    opername("operation_name"),
//    isl("is_lock"),
//    appros("approval_status"),
//    ltime("latest_time"),
//    curid("currency_id"),
//    curname("currency_name"),
//    excr("exchange_rate"),
//    excrd("exchange_rate_date"),

    private String code;

    ModuleFieldInitParamEnum(String code) {
        this.code = code;
    }

    public boolean compareTo(String str) {
        if (str == null) return false;
        return this.toString().equals(str);
    }

    @Override
    public String toString() {
        return this.code;
    }
}
