package com.btjf.credit.report.bo;

import java.io.Serializable;

/**
 * Created by zsw on 2017/6/15.
 *
 * @Description:
 */
public class EmpCreditReportBo extends CreditReportBo implements Serializable {
    private String empName; //员工名字
    private String empMobile;   //员工手机号



    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }
}
