package com.btjf.credit.report.decision.dock.enums;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/13
 * @time 上午10:31
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */
public class DecisionDockEnum {

    /**
     * 调用决策系统接口传入的productId
     */
    public enum Product{
        LIST("查询当前所有可用接口", "100"),
        NAME_IDCARD("姓名身份证二元素", "10000"),
        NAME_IDCARD_MOBILE("姓名身份证手机三要素", "10001"),
        NAME_IDCARD_BANDCARD("姓名身份证银行卡三要素", "10002"),
        FULL_REPORT("大数据征信报告", "10008"),
        ALIPAY_REPORT("支付宝数据", "10004"),
        BAD_REPORT("不良记录", "10006"),
        CREDIT_REPORT("信贷记录", "10007"),
        CHECK_BANKCARD("校验银行卡","10009");


        private String content;
        private String value;

        private Product(String content, String value) {
            this.content = content;
            this.value = value;
        }

        public String getContent() {
            return this.content;
        }

        public String getValue() {
            return this.value;
        }

        public static Product getByValue(String value) {
            Product[] types = Product.values();
            for(Product type : types) {
                if(type.getValue().equals(value)) {
                    return type;
                }
            }
            return null;
        }
    }

    /**
     * 三要素校验时返回提示信息
     */
    public enum ThreeElementCheckCode{
        NAME_CHECK_SAME("一致","1101"),
        NAME_CHECK_NOSAME("不一致","1102"),
        NAME_CHECK_NORESULT("查无结果","1103"),
        INTERFACE_DISABLED("接口不可用","1104");

        private String content;
        private String value;

        private ThreeElementCheckCode(String content, String value) {
            this.content = content;
            this.value = value;
        }

        public String ThreeElementCheckCode() {
            return this.content;
        }

        public String getValue() {
            return this.value;
        }

        public static ThreeElementCheckCode getByValue(String value) {
            ThreeElementCheckCode[] types = ThreeElementCheckCode.values();
            for(ThreeElementCheckCode type : types) {
                if(type.getValue().equals(value)) {
                    return type;
                }
            }
            return null;
        }
    }

    /**
     * 银行卡卡BIN校验
     */
    public enum BankCardBinCode{
        INVALID_BANKCARD("无效卡号","1012"),
        DEPOSIT_CARD("借记卡","1013"),
        DEBIT_CARD("信用卡","1014");

        private String content;
        private String value;

        private BankCardBinCode(String content, String value) {
            this.content = content;
            this.value = value;
        }

        public String BankCardBinCode() {
            return this.content;
        }

        public String getValue() {
            return this.value;
        }

        public static BankCardBinCode getByValue(String value) {
            BankCardBinCode[] types = BankCardBinCode.values();
            for(BankCardBinCode type : types) {
                if(type.getValue().equals(value)) {
                    return type;
                }
            }
            return null;
        }
    }

    public enum ResultCode {
        SUCCESS("成功", "0000"),
        PRODUCT_NOT_EXIST("产品不存在", "1001"),
        MERCHANT_NOT_EXIST("商户不存在", "1002"),
        TIMEOUT("超时请求", "1003"),
        PARAMETER_ERROR("参数格式不对", "1004"),
        DES_ERROR("DES加密错误", "1005"),
        MD5_ERROR("MD5签名错误", "1006"),
        NET_ERROR("网络错误", "1007"),
        SYSTEM_ERROR("系统异常", "1008"),
        DATABASE_ERROR("数据库异常", "1009"),
        INTERFACE_ERROR("接口异常", "1010"),
        DATA_ERROR("数据异常","1011"),
        ONGOING("计算中", "5001"),


        ERROR_NAME_IDCARD("姓名身份证不匹配！","1100"),
        ERROR_MOBILE("手机号与征信人信息不一致！","1101"),
        ERROR_BANKCARD("银行卡与征信人信息不一致！","1102"),
        VALID_BANKCARD_ERROR("校验银行卡错误!","1103");



        private String content;
        private String value;

        private ResultCode(String content, String value) {
            this.content = content;
            this.value = value;
        }

        public String getContent() {
            return this.content;
        }

        public String getValue() {
            return this.value;
        }

        public static ResultCode getByValue(String value) {
            ResultCode[] types = ResultCode.values();
            for(ResultCode type : types) {
                if(type.getValue().equals(value)) {
                    return type;
                }
            }
            return null;
        }
    }

    /**
     * 接口日志返回
     */
    public enum InterfaceType{
        PRODUCT_LIST("获取所有可用决策产品","10001"),
        REAL_NAME_CHECK("姓名身份证二元素验证","10003"),
        MOBILE_REAL_CHECK("姓名身份证手机三要素验证","10004"),
        BANKCARD_REAL_CHECK("姓名身份证银行卡三要素验证","10005"),
        BIGDATA_REPORT_NUM("获取大数据报告编码","10006"),
        ALIPAY_REPORT_NUM("支付宝报告编码","10007"),
        BAD_REPORT_NUM("不良记录报告编码","10008"),
        CREDITRECORD_REPORT_NUM("信贷记录报告编码","10009"),
        BIGDATA_REPORT_CONTENT("获取大数据报告内容","10010"),
        ALIPAY_REPORT_CONTENT("支付宝报告内容","10011"),
        BAD_REPORT_CONTENT("不良记录报告内容","10012"),
        CREDITRECORD_REPORT_CONTENT("信贷记录报告内容","10013"),
        CHECK_BANKCARD("校验银行卡卡BIN","10014"),
        GET_REPORT_CONTENT("获取报告内容","10100"),
        GET_REPORT_NUM("获取报告编号","10101"),
        GET_REPORT_PRODUCT_LIST("获取报告产品开关","10102");

        private String content;
        private String value;

        private InterfaceType(String content, String value) {
            this.content = content;
            this.value = value;
        }

        public String getContent() {
            return this.content;
        }

        public String getValue() {
            return this.value;
        }

        public static InterfaceType getByValue(String value) {
            InterfaceType[] types = InterfaceType.values();
            for(InterfaceType type : types) {
                if(type.getValue().equals(value)) {
                    return type;
                }
            }
            return null;
        }
    }
}
