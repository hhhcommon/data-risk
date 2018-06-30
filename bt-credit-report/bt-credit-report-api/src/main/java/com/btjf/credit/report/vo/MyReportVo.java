package com.btjf.credit.report.vo;

import com.btjf.credit.report.bo.CreditReportBo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2017/6/20.
 *
 * @Description:
 */
public class MyReportVo {
    private String customerName;//征信客户名称
    private String customerIdCardNum;//征信人身份证号
    private String customerMobile;//征信人手机号
    private String customerBankCard;//征信人银行卡
    private Integer status;//查询状态
    private Integer id;//报告ID
    private Boolean isReaded;//是否已读
    private String customerIdCardNumFull;//完整的征信人身份证号
    private String customerMobileFull;//完整的征信人手机号
    private String uuid;//uuid码

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

    public String getCustomerBankCard() {
        return customerBankCard;
    }

    public void setCustomerBankCard(String customerBankCard) {
        this.customerBankCard = customerBankCard;
    }

    public Boolean getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(Boolean readed) {
        isReaded = readed;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
