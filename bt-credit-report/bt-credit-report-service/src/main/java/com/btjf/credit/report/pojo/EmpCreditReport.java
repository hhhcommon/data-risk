package com.btjf.credit.report.pojo;

import com.btjf.credit.report.model.CreditReport;

import java.io.Serializable;

/**
 * 员工信息
 * Created by zsw on 2017/6/15.
 *
 * @Description:
 */
public class EmpCreditReport extends CreditReport implements Serializable{
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
