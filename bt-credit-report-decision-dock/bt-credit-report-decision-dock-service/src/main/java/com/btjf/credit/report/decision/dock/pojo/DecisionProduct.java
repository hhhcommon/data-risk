package com.btjf.credit.report.decision.dock.pojo;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/13
 * @time 下午5:57
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */
public class DecisionProduct {

    private Integer productid;

    private String inputneed;

    private String productdesc;

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getInputneed() {
        return inputneed;
    }

    public void setInputneed(String inputneed) {
        this.inputneed = inputneed;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }
}
