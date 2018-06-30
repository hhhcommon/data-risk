package com.btjf.credit.report.bo;

import java.io.Serializable;

/**
 * Created by zsw on 2017/6/15.
 *
 * @Description:
 */
public class UsableReportBo implements Serializable {
    private String reportName;//征信报告名称
    private Integer reportType;//征信报告类型
    private String reportUrl;//征信报告图标URL

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
}
