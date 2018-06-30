package com.btjf.credit.report.vo;

import com.btjf.credit.report.bo.UsableReportBo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2017/6/20.
 *
 * @Description:
 */
public class UsableReportVo implements Serializable{
    private String reportName;//征信报告名称
    private Integer reportType;//征信报告类型
    private String reportUrl;//征信报告图标URL


    /**
     * @param usableReportBoList
     * @return
     */
    public static List<UsableReportVo> convert(List<UsableReportBo> usableReportBoList) {
        List<UsableReportVo> usableReportVos = null;
        if (usableReportBoList != null) {
            usableReportVos = new ArrayList<UsableReportVo>();
            for (UsableReportBo usableReportBo : usableReportBoList) {
                usableReportVos.add(new UsableReportVo(usableReportBo));
            }
            return usableReportVos;
        }
        return null;
    }

    public UsableReportVo() {
        super();
    }

    public UsableReportVo(UsableReportBo usableReportBo) {
        this.reportName = usableReportBo.getReportName();
        this.reportType = usableReportBo.getReportType();
        this.reportUrl = usableReportBo.getReportUrl();
    }

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
