package com.btjf.credit.report.bo;

import java.io.Serializable;

/**
 * Created by zsw on 2017/6/14.
 *
 * @Description:
 */
public class UnReadReportBo implements Serializable {
    private Integer bigDataUnReadNumber = 0;//大数据报告未读数
    private Integer creditUnReadNumber = 0;//信贷记录未读数
    private Integer badUnReadNumber = 0;//不良记录未读数
    private Integer aliPayUnReadNumber = 0;//支付宝评测未读数

    public Integer getBigDataUnReadNumber() {
        return bigDataUnReadNumber;
    }

    public void setBigDataUnReadNumber(Integer bigDataUnReadNumber) {
        this.bigDataUnReadNumber = bigDataUnReadNumber;
    }

    public Integer getCreditUnReadNumber() {
        return creditUnReadNumber;
    }

    public void setCreditUnReadNumber(Integer creditUnReadNumber) {
        this.creditUnReadNumber = creditUnReadNumber;
    }

    public Integer getBadUnReadNumber() {
        return badUnReadNumber;
    }

    public void setBadUnReadNumber(Integer badUnReadNumber) {
        this.badUnReadNumber = badUnReadNumber;
    }

    public Integer getAliPayUnReadNumber() {
        return aliPayUnReadNumber;
    }

    public void setAliPayUnReadNumber(Integer aliPayUnReadNumber) {
        this.aliPayUnReadNumber = aliPayUnReadNumber;
    }
}
