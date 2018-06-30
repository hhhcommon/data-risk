package com.btjf.credit.report.vo;

import com.btjf.credit.report.bo.CreditReportBo;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;

/**
 * Created by zsw on 2017/6/20.
 *
 * @Description:
 */
public class ReportDetailsVo implements Serializable{
    private String customerName;//征信客户名称
    private String customerIdCardNum;//征信人身份证号
    private String reportNum;//征信报告编号
    private String reportContent;//征信报告内容
    private String customerMobile;//征信人手机号
    private Integer id;//t_CreditReport.id主键
    private Integer reportType;//报告类型
    private Boolean isFeedBacked;//是否已反馈
    private Boolean isBigDataEnabled;//大数据报告是否可用

    private String customerIdCardNumFull;//完整的征信人身份证号
    private String customerMobileFull;//完整的征信人手机号

    public ReportDetailsVo(){
        super();
    }

    public ReportDetailsVo(CreditReportBo creditReportBo) {
        if(creditReportBo == null){
            return;
        }

        this.customerName = creditReportBo.getCustomerName();
        this.customerIdCardNum = creditReportBo.getCustomerIdCardNum();
        this.reportNum = creditReportBo.getReportNum();
        this.reportContent = creditReportBo.getReportContent();
        this.customerMobile = creditReportBo.getCustomerMobile();
        this.id = creditReportBo.getId();
        this.reportType = creditReportBo.getReportType();
        this.isFeedBacked = creditReportBo.getIsFeedBacked();
        this.customerIdCardNumFull = creditReportBo.getCustomerIdCardNumFull();
        this.customerMobileFull = creditReportBo.getCustomerMobileFull();
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
    public Boolean getIsBigDataEnabled() {
        return isBigDataEnabled;
    }

    public void setIsBigDataEnabled(Boolean bigDataEnabled) {
        isBigDataEnabled = bigDataEnabled;
    }

    public Boolean getIsFeedBacked() {
        return isFeedBacked;
    }

    public void setIsFeedBacked(Boolean feedBacked) {
        isFeedBacked = feedBacked;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
