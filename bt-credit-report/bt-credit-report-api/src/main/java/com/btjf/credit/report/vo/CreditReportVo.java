package com.btjf.credit.report.vo;

import com.btjf.credit.report.bo.CreditReportBo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2017/6/19.
 *
 * @Description:
 */
public class CreditReportVo implements Serializable{
    private String customerName;//征信客户名称
    private String customerIdCardNum;//征信人身份证号
    private String customerMobile;//征信人手机号
    private Integer id;//t_CreditReport.id主键
    private String uuid;//uuid码

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
