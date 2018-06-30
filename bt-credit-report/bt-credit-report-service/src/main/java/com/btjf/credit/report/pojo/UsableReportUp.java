package com.btjf.credit.report.pojo;

import java.util.List;

/**
 * 在大数据报告点击查询返回其他报告列表使用
 * Created by zsw on 2017/6/15.
 *
 * @Description:
 */
public class UsableReportUp {
    private Integer id;//报告ID
    private String message;//提示语
    private List<UsableReport> usableReports;

    public List<UsableReport> getUsableReports() {
        return usableReports;
    }

    public void setUsableReports(List<UsableReport> usableReports) {
        this.usableReports = usableReports;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
