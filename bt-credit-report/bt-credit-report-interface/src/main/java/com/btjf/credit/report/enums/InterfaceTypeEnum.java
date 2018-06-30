package com.btjf.credit.report.enums;

import com.btjf.common.enums.ContentEnum;

/**
 * 接口类型枚举
 * Created by zsw on 2017/6/17.
 *
 * @Description:
 */
public enum InterfaceTypeEnum implements ContentEnum {
    UNREAD_REPORT("首页大数据、信贷、不良记录、支付宝评测报告未读数统计", 101),
    MAIN_PAGE_ENABLED("首页点击产品时，报告类型获取报告可查开关",102),
    CREATE_ENABLED("新建征信报告时，报告类型获取报告可查开关",103),
    USABLED_REPORT("获取可用的征信报告列表",104),
    MYREPORT_LIST("获取我的查询报告列表",105),
    STAFF_REPORT_LIST("获取员工查询报告列表",106),
    SEARCH_REPORT("搜索征信报告列表",112),
    REPORT_DETAIL("获取征信报告详情",107),
    CUSTOMER_LIST("获取已查询过征信的客户列表",109),
    LAST_REPORT("获取最新报告",108),
    CREATE_REPORT("创建报告",113),
    GET_REPORT_NUM("通过征信信息,对接决策系统获取报告编号",114),
    REPORT_BACK("征信报告反馈",110),
    SEARCH_REPORT_LIST("搜索征信人报告列表",111);

    private String content;
    private Integer value;

    private InterfaceTypeEnum(String content, Integer value) {
        this.content = content;
        this.value = value;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public static InterfaceTypeEnum getByValue(Integer value) {
        InterfaceTypeEnum[] types = InterfaceTypeEnum.values();
        for(InterfaceTypeEnum type : types) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
