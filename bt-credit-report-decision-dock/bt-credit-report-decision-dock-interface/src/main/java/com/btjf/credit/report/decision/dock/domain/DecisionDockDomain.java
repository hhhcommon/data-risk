package com.btjf.credit.report.decision.dock.domain;

import com.btjf.credit.report.decision.dock.bo.DecisionProductBo;

import java.util.List;

/**
 * 信用报告相关业务
 * Created by zsw on 2017/6/12.
 *
 * @Description:
 */
public interface DecisionDockDomain {

    /**
     * 获取所有可用决策产品
     * @return
     */
    public List<DecisionProductBo> getProductList();

    /**
     * 姓名身份证二元素验证
     * @param name  姓名
     * @param idCard    身份证
     * @return
     */
    public String realNameCheck(String name, String idCard);


    /**
     * 姓名身份证手机三要素验证
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @return
     */
    public String mobileRealCheck(String name, String idCard, String mobile);

    /**
     * 姓名身份证银行卡三要素验证
     * @param name  姓名
     * @param idCard    身份证
     * @param bankCard  银行卡
     * @return
     */
    public String bankCardRealCheck(String name, String idCard, String bankCard);

    /**
     * 校验银行卡
     * @param bankCard  银行卡
     * @return
     */
    public String checkBankCard(String bankCard);

    /**
     * 获取大数据报告编号
     * @param name  姓名
     * @param idCard    身份证
     * @param bankCard  银行卡
     * @param mobile    手机号
     * @return
     */
    public String bigDataReportNum(String name, String idCard, String bankCard,String mobile);

    /**
     * 支付宝报告编号
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @return
     */
    public String aliPayReportNum(String name, String idCard,String mobile);

    /**
     * 不良记录报告编号
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @return
     */
    public String badReportNum(String name, String idCard,String mobile);

    /**
     * 信贷记录报告编号
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @return
     */
    public String creditRecordReportNum(String name, String idCard,String mobile);

    /**
     * 获取大数据报告内容
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @param reportNo  报告编号
     * @return
     */
    public String bigDataReportContent(String name, String idCard, String mobile, String reportNo);

    /**
     * 支付宝报告内容
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @param reportNo  报告编号
     * @return
     */
    public String aliPayReportContent(String name, String idCard,String mobile, String reportNo);

    /**
     * 不良记录报告内容
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @param reportNo  报告编号
     * @return
     */
    public String badReportContent(String name, String idCard,String mobile, String reportNo);

    /**
     * 信贷记录报告内容
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @param reportNo  报告编号
     * @return
     */
    public String creditRecordReportContent(String name, String idCard,String mobile, String reportNo);


}
