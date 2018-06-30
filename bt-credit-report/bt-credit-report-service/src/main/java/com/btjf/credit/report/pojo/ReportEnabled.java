package com.btjf.credit.report.pojo;

/**
 * Created by zsw on 2017/6/15.
 *
 * @Description:
 */
public class ReportEnabled {
    private Boolean bigDataEnabled;//大数据征信报告查询是否开启
    private Boolean creditEnabled;//信贷记录查询是否开启
    private Boolean badEnabled;//不良记录查询是否开启
    private Boolean aliPayEnabled;//支付宝评测查询是否开启

    public Boolean getBigDataEnabled() {
        return bigDataEnabled;
    }

    public void setBigDataEnabled(Boolean bigDataEnabled) {
        this.bigDataEnabled = bigDataEnabled;
    }

    public Boolean getCreditEnabled() {
        return creditEnabled;
    }

    public void setCreditEnabled(Boolean creditEnabled) {
        this.creditEnabled = creditEnabled;
    }

    public Boolean getBadEnabled() {
        return badEnabled;
    }

    public void setBadEnabled(Boolean badEnabled) {
        this.badEnabled = badEnabled;
    }

    public Boolean getAliPayEnabled() {
        return aliPayEnabled;
    }

    public void setAliPayEnabled(Boolean aliPayEnabled) {
        this.aliPayEnabled = aliPayEnabled;
    }
}
