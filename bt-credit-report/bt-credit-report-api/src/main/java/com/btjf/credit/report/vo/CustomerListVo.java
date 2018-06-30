package com.btjf.credit.report.vo;

import com.btjf.credit.report.bo.CreditReportBo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2017/6/20.
 *
 * @Description:
 */
public class CustomerListVo {
    private String customerName;//征信客户名称
    private String customerIdCardNum;//征信人身份证号

    private String customerIdCardNumFull;//完整的征信人身份证号
    private String customerMobileFull;//完整的征信人手机号


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

}
