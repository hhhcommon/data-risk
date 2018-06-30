package com.btjf.credit.report.bo;

import java.io.Serializable;

/**
 * Created by zsw on 2017/6/17.
 *
 */
public class CreditReportApplyBo implements Serializable{
    private Integer reportType;//报告类型
    private String customerName;//征信人姓名
    private String customerIdCardNum;//征信人身份证
    private String customerMobile;//征信人手机号

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

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }
}
