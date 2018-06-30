package com.btjf.credit.report.vo;

import java.io.Serializable;

/**
 * 新建大数据报告失败时，返回其他报告实例对象
 * Created by zsw on 2017/6/29.
 *
 * @Description:
 */
public class CreateUsableReportVo implements Serializable {
    private String reportName;//征信报告名称
    private Integer reportType;//征信报告类型

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }
}
