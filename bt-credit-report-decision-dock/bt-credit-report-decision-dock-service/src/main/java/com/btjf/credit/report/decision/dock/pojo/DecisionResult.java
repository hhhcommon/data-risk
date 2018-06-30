package com.btjf.credit.report.decision.dock.pojo;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/13
 * @time 上午10:22
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */
public class DecisionResult {

    private String resultCode;

    private String resultDesc;

    private String data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
