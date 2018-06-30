package com.btjf.credit.report.enums;


import com.btjf.common.enums.ContentEnum;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/12
 * @time 下午8:08
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */
public class CreditReportEnum {

    public enum Type implements ContentEnum {
        FULL("大数据征信报告", 10008),
        CREDIT("信贷记录", 10007),
        BAD("不良记录", 10006),
        ALIPAY("支付宝评测", 10004);


        private String content;
        private Integer value;

        private Type(String content, Integer value) {
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

        public static Type getByValue(Integer value) {
            Type[] types = Type.values();
            for(Type type : types) {
                if(type.getValue().equals(value)) {
                    return type;
                }
            }
            return null;
        }

    }


    public enum Status implements ContentEnum{
        ONGOING("查询中", 0),
        SUCCESS("查询成功", 1),
        FAIL("查询失败", 2),
        EXPIRE("已过期",3);

        private String content;
        private Integer value;

        private Status(String content, Integer value) {
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

        public static Status getByValue(Integer value) {
            Status[] types = Status.values();
            for(Status type : types) {
                if(type.getValue().equals(value)) {
                    return type;
                }
            }
            return null;
        }

    }

    public enum SelectReportType implements ContentEnum{
        CONTINUE("点击继续按钮查询报告", 1),
        IMMEDIATELY_QUERY("点击立即查询按钮", 2);

        private String content;
        private Integer value;

        private SelectReportType(String content, Integer value) {
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

        public static SelectReportType getByValue(Integer value) {
            SelectReportType[] types = SelectReportType.values();
            for(SelectReportType type : types) {
                if(type.getValue().equals(value)) {
                    return type;
                }
            }
            return null;
        }
    }

    public enum StatusException implements ContentEnum{
        SELECT_NO_ENABLED("该查询服务正在努力建设中，暂不可查询",1001),
        LIMIT_CUSTOMER_COUNT("每天仅限查询5位客户",1002),
        LIMIT_SELECT_TIME("3分钟内仅限查询1位客户",1003),

        EMP_NO_IN_DEPT("该员工没有所属部门",1005),

        RESOLVE_FAIL("解析报告内容失败",1007),
        REPORT_SELECTGING("报告正在查询中",1008),
        MOBILE_DIFF("姓名手机号不相符或无法验证，是否继续查询？",1010),
        BANKCARD_ERROR("银行卡信息有误，请核对后重新填写",1011),
        NO_DEBIT_CARD("不可使用信用卡，请填写储蓄卡查询",1025),
        MOBILE_ERROR("手机号与征信人信息不一致！",1026),

        ERROR_REPORT("报告信息有误",1019),
        OTHER_ERROR("其他错误",1012),

        NO_EMP("该员工不存在",1004),
        NO_REPORT_DETAIL("未获取到该报告详情",1006),
        NO_SEE_REPORT("无权查看此报告！",1020),
        EXPIRY_REPORT("该报告已失效",1021),
        NO_PRODUCT("未获取到该产品类型",1013),
        NO_SELECT("抱歉，当前征信信息不可查，请稍后再试。",1022),
        NO_BANKCARD("未填写客户储蓄卡，仅可查询以下信息",1023),
        NO_SELECT_BIGDATA("暂不可查询大数据信息，仅可查询以下信息",1014),

        NOT_NULL_REPORTTYPE("报告类型不能为空",1015),
        NOT_NULL_CREDITNAME("征信人名字不能为空",1016),
        NOT_NULL_CREDITIDCARD("征信人身份号不能为空",1017),
        NOT_NULL_CREDITMOBILE("征信人手机号不能为空",1018),
        NOT_NULL_REPORTNUM("征信报告编号不能为空",1024);

        private String content;
        private Integer value;

        private StatusException(String content, Integer value) {
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

        public static StatusException getByValue(Integer value) {
            StatusException[] statusExceptions = StatusException.values();
            for(StatusException statusException : statusExceptions) {
                if(statusException.getValue().equals(value)) {
                    return statusException;
                }
            }
            return null;
        }
    }

    /**
     * 银行卡校验时推送的提示消息
     */
    public enum BankCardRealCheckCode implements ContentEnum{
        SAME("一致", 1101),
        DIFF("银行卡与征信人信息不一致！", 1102),
        NORESULT("查询不到银行卡信息，请更换银行卡！", 1103),
        INTERFACE_ERROR("查询不到银行卡信息，请联系客服！", 1104);

        private String content;
        private Integer value;

        private BankCardRealCheckCode(String content, Integer value) {
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

        public static BankCardRealCheckCode getByValue(Integer value) {
            BankCardRealCheckCode[] types = BankCardRealCheckCode.values();
            for(BankCardRealCheckCode type : types) {
                if(type.getValue().equals(value)) {
                    return type;
                }
            }
            return null;
        }

    }

    /**
     * 姓名身份证校验提示语
     */
    public enum RealNameCheck implements ContentEnum{
        SAME("一致", 1101),
        DIFF("您填写的信息不一致，请确认后重新填写！", 1102),
        NORESULT("查无结果！", 1103),
        INTERFACE_ERROR("查询不到用户信息，请联系客服！", 1104);

        private String content;
        private Integer value;

        private RealNameCheck(String content, Integer value) {
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

        public static RealNameCheck getByValue(Integer value) {
            RealNameCheck[] types = RealNameCheck.values();
            for(RealNameCheck type : types) {
                if(type.getValue().equals(value)) {
                    return type;
                }
            }
            return null;
        }

    }

    /**
     * 手机号校验提示语
     */
    public enum MobileRealCheck implements ContentEnum{
        SAME("一致", 1101),
        DIFF("姓名手机号不相符，是否继续查询？", 1102),
        NORESULT("查无此号或手机号非实名认证，是否继续查询？", 1103),
        INTERFACE_ERROR("查询不到征信信息，请联系客服！", 1104);

        private String content;
        private Integer value;

        private MobileRealCheck(String content, Integer value) {
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

        public static MobileRealCheck getByValue(Integer value) {
            MobileRealCheck[] types = MobileRealCheck.values();
            for(MobileRealCheck type : types) {
                if(type.getValue().equals(value)) {
                    return type;
                }
            }
            return null;
        }

    }

}
