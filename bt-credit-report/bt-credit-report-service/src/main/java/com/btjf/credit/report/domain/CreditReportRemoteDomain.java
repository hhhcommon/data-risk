package com.btjf.credit.report.domain;

import com.btjf.common.page.Page;
import com.btjf.credit.report.bo.CreditReportBo;
import com.btjf.credit.report.bo.EmpCreditReportBo;
import com.btjf.credit.report.bo.UnReadReportBo;
import com.btjf.credit.report.bo.UsableReportBo;
import com.btjf.credit.report.model.CreditReport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/12
 * @time 下午9:02
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */
@Service("creditReportDomain")
public class CreditReportRemoteDomain implements CreditReportDomain{
    @Resource
    private CreditReportDomain creditReportNativeDomain;

    @Override
    public UnReadReportBo getUnReadReport(Integer empId) {
        return creditReportNativeDomain.getUnReadReport(empId);
    }

    @Override
    public Boolean getProductIsUseful(Integer empId, Integer reportType) {
        return creditReportNativeDomain.getProductIsUseful(empId,reportType);
    }

    @Override
    public Boolean getCreateEnabled(Integer empId, Integer reportType) {
        return creditReportNativeDomain.getCreateEnabled(empId,reportType);
    }

    @Override
    public Page<CreditReportBo> findMyReportList(Integer empId, Integer reportType, Page page) {
        return creditReportNativeDomain.findMyReportList(empId,reportType,page);
    }

    @Override
    public Page<EmpCreditReportBo> findStaffReportList(Integer empId, Integer reportType, Page page) {
        return creditReportNativeDomain.findStaffReportList(empId,reportType,page);
    }

    @Override
    public Page<EmpCreditReportBo> searchReportList(Integer empId, String queryStr, Integer reportType, Page page) {
        return creditReportNativeDomain.searchReportList(empId,queryStr,reportType,page);
    }

    @Override
    public CreditReportBo getReportDetails(Integer empId, String uuid) {
        return creditReportNativeDomain.getReportDetails(empId,uuid);
    }

    @Override
    public String getLastReport(Integer empId, String customerName, String customerIdCardNum, String customerMobile, Integer reportType) {
        return creditReportNativeDomain.getLastReport(empId,customerName,customerIdCardNum,customerMobile,reportType);
    }

    @Override
    public Page<CreditReportBo> findCustomerList(Integer empId, String querierInfo, Page page) {
        return creditReportNativeDomain.findCustomerList(empId,querierInfo,page);
    }

    @Override
    public Boolean reportBack(String uuid, Boolean isUseful) {
        return creditReportNativeDomain.reportBack(uuid,isUseful);
    }

    @Override
    public String createCreditReport(Integer empId, String customerName, String customerIdCardNum, String customerMobile,
                                   String customerBankCard, Integer reportType,Integer requestType) {
        return creditReportNativeDomain.createCreditReport(empId,customerName,customerIdCardNum,customerMobile,customerBankCard,reportType,requestType);
    }

    @Override
    public List<UsableReportBo> findUsableReport() {
        return creditReportNativeDomain.findUsableReport();
    }

    @Override
    public CreditReportBo reportContentBack(String resultCode, String resultDesc, String reportNum, String reportContent) {
        return creditReportNativeDomain.reportContentBack(resultCode,resultDesc,reportNum,reportContent);
    }

    @Override
    public List<CreditReportBo> findSelectingReport() {
        return creditReportNativeDomain.findSelectingReport();
    }

    @Override
    public String bigDataReportContent(String name, String idCard, String mobile, String reportNo) {
        return creditReportNativeDomain.bigDataReportContent(name,idCard,mobile,reportNo);
    }

    @Override
    public String aliPayReportContent(String name, String idCard, String mobile, String reportNo) {
        return creditReportNativeDomain.aliPayReportContent(name,idCard,mobile,reportNo);
    }

    @Override
    public String badReportContent(String name, String idCard, String mobile, String reportNo) {
        return creditReportNativeDomain.badReportContent(name,idCard,mobile,reportNo);
    }

    @Override
    public String creditRecordReportContent(String name, String idCard, String mobile, String reportNo) {
        return creditReportNativeDomain.creditRecordReportContent(name,idCard,mobile,reportNo);
    }

    @Override
    public void inQueryDataSync() {
        creditReportNativeDomain.inQueryDataSync();
    }

}
