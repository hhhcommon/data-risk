package com.btjf.credit.report.base.controller;

import com.btjf.application.components.controller.BaseController;
import com.btjf.common.utils.BeanUtil;
import com.btjf.credit.report.base.entity.Emp;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/19
 * @time 上午10:40
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */
public class CreditBaseController extends BaseController{
    /**
     * 获取当前登录的员工ID
     *
     * @return
     */
    public Integer getCurrentUserID() {
        Emp emp = getEmp();
        return emp != null ? emp.getEmpID() : null;
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return
     */
    public Emp getEmp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Object object = request.getAttribute("emp");
        Emp emp = BeanUtil.convert(object, Emp.class);
        return emp;
    }

    public Integer getCurrentUserID(Integer userID) {
        return userID == null ? this.getCurrentUserID() : userID;
    }
}
