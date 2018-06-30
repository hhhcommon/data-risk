package com.btjf.credit.report.domian;

import com.alibaba.fastjson.JSON;
import com.btjf.common.page.Page;
import com.btjf.credit.report.bo.CreditReportBo;
import com.btjf.credit.report.bo.EmpCreditReportBo;
import com.btjf.credit.report.bo.UnReadReportBo;
import com.btjf.credit.report.bo.UsableReportBo;
import com.btjf.credit.report.domain.CreditReportRemoteDomain;
import com.btjf.credit.report.enums.CreditReportEnum;
import com.btjf.credit.report.exception.CreditReportExcetion;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zsw on 2017/6/17.
 *
 *  征信报告单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class CreditReportNativeDomainTest {

    @Resource
    private CreditReportRemoteDomain creditReportDomain;






    /**
     * 获取员工所有类型报告未读数
     */
    @Test
    public void getUnReadReport(){
        UnReadReportBo unReadReportBo = creditReportDomain.getUnReadReport(empId);
        System.err.println(JSON.toJSONString(unReadReportBo));
        assert unReadReportBo.getBadUnReadNumber() == 0;
    }


    /**
     * 根据用户，判断产品是否可用
     */
    @Test
    public void getProductIsUseful(){
        Boolean productIsUseful = creditReportDomain.getProductIsUseful(empId, CreditReportEnum.Type.FULL.getValue());
        System.err.println(JSON.toJSONString(productIsUseful));

    }

    /**
     * 点击新建报告时，返回报告是否可用
     */
    @Test
    public void getCreateEnabled(){
        Boolean createEnabled = creditReportDomain.getCreateEnabled(empId, CreditReportEnum.Type.FULL.getValue());
        System.err.println(createEnabled);
    }

    /**
     * 获取可用的征信报告列表
     */
    @Test
    public void findUsableReport(){
        List<UsableReportBo> usableReport = creditReportDomain.findUsableReport();
        System.err.println(JSON.toJSONString(usableReport));
    }

    /**
     * 我的查询报告列表
     */
    @Test
    public void findMyReportList(){
        Page page = new Page(pageSize, currentPage);
        Page<CreditReportBo> myReportList = creditReportDomain.findMyReportList(empId, reportType, page);
        System.err.println(JSON.toJSONString(myReportList));
    }

    /**
     * 员工查询报告列表
     */
    @Test
    public void findStaffReportList(){
        Page page = new Page(pageSize, currentPage);
        Page<EmpCreditReportBo> staffReportList = creditReportDomain.findStaffReportList(empId, reportType, page);
        System.err.println(JSON.toJSONString(staffReportList));
    }

    /**
     * 员搜索征信报告列表
     */
    @Test
    public void searchReportList(){
        Page page = new Page(pageSize, currentPage);
        Page<EmpCreditReportBo> staffReportList = creditReportDomain.searchReportList(empId, queryStr, reportType, page);
        System.err.println(JSON.toJSONString(staffReportList));
    }

//    /**
//     * 获取报告详情
//     */
//    @Test
//    public void getReportDetails(){
//          //com.btjf.credit.report.exception.CreditReportExcetion: 无权查看此报告！
//        CreditReportBo reportDetails = creditReportDomain.getReportDetails(empId, id);
//        System.err.println(JSON.toJSONString(reportDetails));
//    }

    /**
     * 获取客户列表
     */
    @Test
    public void findCustomerList(){
        Page page = new Page(pageSize, currentPage);
        Page<CreditReportBo> customerList = creditReportDomain.findCustomerList(empId, queryStr, page);
        System.err.println(JSON.toJSONString(customerList));
    }

//    /**
//     * 获取最新报告
//     */
//    @Test
//    public void getLastReport(){
//        Integer report = creditReportDomain.getLastReport(empId, customerName, customerIdCardNum, customerMobile, reportType);
//        System.err.println(JSON.toJSONString(report));
//    }

    //报告类型
    private static final Integer reportType = 10006;

    /**
     * 创建报告
     */
    @Test
    public void createCreditReport(){
        //com.btjf.credit.report.exception.CreditReportExcetion: 银行卡信息有误，请核对后重新填写
        String creditReport = creditReportDomain.createCreditReport(empId, customerName, customerIdCardNum, customerMobile, customerBankCard, reportType, requestType);
        System.err.println(creditReport);
    }

    private static final Integer currentPage = 1;
    private static final Integer pageSize = 20;
    //搜索关键字
    private static final String queryStr = "";


    //银行卡
    private static final String customerBankCard = "6217001820029153347";

    //报告ID
    private static final Integer id = 60;
    //是否有用
    private static final Boolean isUseful = true;

    //请求类型
    private static final Integer requestType = 1;
    /**
     * 报告反馈
     */
//    @Test
//    public void reportBack(){
//        Boolean aBoolean = creditReportDomain.reportBack(id, isUseful);
//        System.err.println(JSON.toJSONString(aBoolean));
//    }

    //员工ID
    private static final Integer empId = 2772;

    //姓名
    private static final String customerName = "测试二";
    //身份证
    private static final String customerIdCardNum = "21120019890827424";
    //手机号
    private static final String customerMobile = "19995874369";
    //报告编号
    private static final String reportNo = "2017081016121500011012371502358545409";
    @Test
    public void bigDataReportContent(){
        String bigDataReportContent = creditReportDomain.bigDataReportContent(customerName, customerIdCardNum, customerMobile, reportNo);
        System.err.println(bigDataReportContent);
    }

    /**
     * 获取查询中的报告集合
     */
    @Test
    public void findSelectingReport(){
        List<CreditReportBo> selectingReport = creditReportDomain.findSelectingReport();
        System.err.println(selectingReport);
    }

//    @Test
//    public void badReportContent(){
//        String badReportContent = creditReportDomain.badReportContent(customerName, customerIdCardNum, customerMobile, reportNo);
//        System.err.println(badReportContent);
//    }



    @Test
    public void getReportDetails(){
        CreditReportBo creditReportBo = creditReportDomain.getReportDetails(empId,"7fa61404-1542-42a0-b16b-57ffe705de96");
        System.err.println(creditReportBo);
    }

    @Test
    public void inQueryDataSync(){
        creditReportDomain.inQueryDataSync();
    }


    public static void main(String[] args) {
        System.err.println(!StringUtils.isBlank("SUCCESS") && !"SUCCESS".equals("SUCCESS"));
    }
}
