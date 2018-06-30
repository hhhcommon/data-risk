package com.btjf.credit.report.vo;

import com.btjf.credit.report.bo.EmpCreditReportBo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2017/6/19.
 *
 * @Description:
 */
public class EmpCreditReportVo extends CreditReportVo implements Serializable{

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
