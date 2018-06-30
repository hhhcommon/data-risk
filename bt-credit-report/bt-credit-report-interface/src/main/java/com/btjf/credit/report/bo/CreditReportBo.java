package com.btjf.credit.report.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/13
 * @time 上午9:40
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */
public class CreditReportBo implements Serializable {

    private String customerName;//征信客户名称
    private String customerIdCardNum;//征信人身份证号
    private String reportNum;//征信报告编号
    private String reportContent;//征信报告内容
    private String customerMobile;//征信人手机号
    private Integer status;//查询状态
    private Integer id;//t_CreditReport.id主键
    private Integer reportType;//报告类型
    private Boolean isUseful;//报告是否有用
    private String customerBankCard;//征信人银行卡
    private String reportCode;//报告编码
    private Boolean isReaded;//是否已读
    private Boolean isFeedBacked;//是否已反馈

    private Date applyTime;//报告生成时间

    private String customerIdCardNumFull;//完整的征信人身份证号
    private String customerMobileFull;//完整的征信人手机号

    private String uuid;//uuid码

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getCustomerIdCardNumFull() {
        return customerIdCardNumFull;
    }

    public void setCustomerIdCardNumFull(String customerIdCardNumFull) {
        this.customerIdCardNumFull = customerIdCardNumFull;
    }

    public String getCustomerMobileFull() {
        return customerMobileFull;
    }

    public void setCustomerMobileFull(String customerMobileFull) {
        this.customerMobileFull = customerMobileFull;
    }

    public Boolean getIsFeedBacked() {
        return isFeedBacked;
    }

    public void setIsFeedBacked(Boolean feedBacked) {
        isFeedBacked = feedBacked;
    }

    public Boolean getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(Boolean readed) {
        isReaded = readed;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getCustomerBankCard() {
        return customerBankCard;
    }

    public void setCustomerBankCard(String customerBankCard) {
        this.customerBankCard = customerBankCard;
    }

    public Boolean getIsUseful() {
        return isUseful;
    }

    public void setIsUseful(Boolean isUseful) {
        this.isUseful = isUseful;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerIdCardNum() {
        return customerIdCardNum;
    }

    public void setCustomerIdCardNum(String customerIdCardNum) {
        this.customerIdCardNum = customerIdCardNum;
    }

    public String getReportNum() {
        return reportNum;
    }

    public void setReportNum(String reportNum) {
        this.reportNum = reportNum;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
