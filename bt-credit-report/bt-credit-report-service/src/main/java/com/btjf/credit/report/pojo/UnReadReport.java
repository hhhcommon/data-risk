package com.btjf.credit.report.pojo;

/**
 * Created by zsw on 2017/6/14.
 *
 * @Description:
 */
public class UnReadReport {
    private Integer reportCount;//未读数量
    private Integer reportType;//报告类型

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }
}
