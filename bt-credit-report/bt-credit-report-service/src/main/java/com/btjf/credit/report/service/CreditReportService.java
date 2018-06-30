package com.btjf.credit.report.service;

import com.btjf.business.organize.emp.enums.EmpEnums;
import com.btjf.common.page.Page;
import com.btjf.common.utils.DateUtil;
import com.btjf.credit.report.enums.CreditReportEnum;
import com.btjf.credit.report.mapper.CreditReportMapper;
import com.btjf.credit.report.model.CreditReport;
import com.btjf.credit.report.model.CreditReportExample;
import com.btjf.credit.report.pojo.EmpCreditReport;
import com.btjf.credit.report.pojo.UnReadReport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/12
 * @time 下午8:22
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */
@Service
public class CreditReportService {

    @Resource
    private CreditReportMapper creditReportMapper;

    /**
     * 获取员工所有报告类型未读数
     * @param empId 员工ID
     * @return  List<UnReadReport>
     */
    public List<UnReadReport> getUnReadReport(Integer empId){
        return  creditReportMapper.getUnReadReport(empId);
    }

    /**
     * 员工ID、报告类型获取报告列表
     * @param empId 员工ID集合
     * @param reportType    报告类型
     * @return  Page<CreditReport>
     */
    public Page<CreditReport> findReportListByEmpIdAndType(Integer empId, Integer reportType, Integer currentPage, Integer pageSize){

        CreditReportExample example = new CreditReportExample();
        CreditReportExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdEqualTo(empId);
        criteria.andReportTypeEqualTo(reportType);
        criteria.andIsDeleteEqualTo(Boolean.FALSE);

        example.setOrderByClause("FApplyTime desc");

        PageHelper.startPage(currentPage, pageSize);//mybatis默认分页从1开始，返回时再扣1 TODO 改了

        List<CreditReport> creditReports = this.creditReportMapper.selectByExample(example);

        PageInfo<CreditReport> creditReportPageInfo = new PageInfo<>(creditReports);

        return new Page<>(pageSize, currentPage,
                creditReportPageInfo.getList(), (int)creditReportPageInfo.getTotal());
    }

    /**
     * 搜索报告列表
     * @param queryStr  征信人姓名模糊、身份证号全量匹配关键字
     * @param reportType    报告类型
     * @return  Page<EmpCreditReport>
     */
    public Page<EmpCreditReport> searchReportList(Integer empId, Integer deptId, String queryStr, Integer reportType,
                                                  EmpEnums.Position position, Integer currentPage, Integer pageSize,Integer showMonth){


        PageHelper.startPage(currentPage, pageSize);

        List<EmpCreditReport> empCreditReports = this.creditReportMapper.searchReportList(empId, deptId, queryStr,
                reportType, position.getValue(),showMonth);


        PageInfo<EmpCreditReport> creditReportPageInfo = new PageInfo<>(empCreditReports);

        return new Page<>(pageSize, currentPage,
                creditReportPageInfo.getList(), (int)creditReportPageInfo.getTotal());
    }

    /**
     * 获取下级员工查询记录列表
     * @param empId 员工ID
     * @param deptId    部门ID
     * @param reportType    报告类型
     * @param position  员工职位
     * @return  Page<EmpCreditReport>
     */
    public Page<EmpCreditReport> findSubEmpReport(Integer empId, Integer deptId, Integer reportType, EmpEnums.Position position,
                                                  Integer currentPage, Integer pageSize,Integer showMonth){
        PageHelper.startPage(currentPage, pageSize);

        List<EmpCreditReport> empCreditReports = this.creditReportMapper.findSubEmpReport(empId, deptId, reportType,
                position.getValue(),showMonth);

        PageInfo<EmpCreditReport> creditReportPageInfo = new PageInfo<>(empCreditReports);

        return new Page<>(pageSize, currentPage,
                creditReportPageInfo.getList(), (int)creditReportPageInfo.getTotal());
    }

    /**
     * 员工ID集合获取该类型报告数
     * @param empId    员工ID集合
     * @param reportType    报告类型
     * @return  Integer
     */
    public Integer findReportListCount(Integer empId, Integer reportType){
        CreditReportExample example = new CreditReportExample();
        CreditReportExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdEqualTo(empId);
        criteria.andReportTypeEqualTo(reportType);
        criteria.andIsDeleteEqualTo(Boolean.FALSE);

        return Math.toIntExact(this.creditReportMapper.countByExample(example));
    }

    /**
     * 获取征信报告详情
     * @param uuid    报告UUID码
     * @return  CreditReport
     */
    public CreditReport getReportDetails(String uuid){
        CreditReportExample example = new CreditReportExample();
        CreditReportExample.Criteria criteria = example.createCriteria();
        criteria.andUuidEqualTo(uuid);
        Date dateBefore = DateUtil.dateBefore(new Date(), Calendar.MONTH, 3);
        criteria.andApplyTimeGreaterThan(dateBefore);

        List<CreditReport> creditReports = this.creditReportMapper.selectByExampleWithBLOBs(example);
        if(creditReports ==null || creditReports.size() == 0){
            return null;
        }
        return creditReports.get(0);
    }

    /**
     * 修改报告为已读
     * @param empId 员工ID
     * @param customerIdCardNum 征信人身份证
     * @param reportType    报告类型
     */
    public void updateReportReaded(Integer empId,String customerIdCardNum,Integer reportType){
        CreditReport creditReport = new CreditReport();
        creditReport.setIsReaded(true);
        creditReport.setReadTime(new Date());

        CreditReportExample example = new CreditReportExample();
        CreditReportExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdEqualTo(empId);
        criteria.andCustomerIdCardNumEqualTo(customerIdCardNum);
        criteria.andReportTypeEqualTo(reportType);

        this.creditReportMapper.updateByExampleSelective(creditReport,example);
    }
    /**
     * 获取最近一次报告
     * @param empId 员工ID
     * @param customerName 征信人姓名
     * @param customerIdCardNum 征信人身份证
     * @param customerMobile    征信人手机号
     * @param reportType    报告类型
     * @return  CreditReport
     */
    public CreditReport getLastReport(Integer empId, String customerName, String customerIdCardNum,
                                             String customerMobile, Integer reportType){
        CreditReportExample example = new CreditReportExample();
        CreditReportExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdEqualTo(empId);
        criteria.andCustomerNameEqualTo(customerName);
        criteria.andCustomerIdCardNumEqualTo(customerIdCardNum);
        criteria.andCustomerMobileEqualTo(customerMobile);
        criteria.andReportTypeEqualTo(reportType);
        example.setOrderByClause("FApplyTime DESC");
        List<CreditReport> creditReports = this.creditReportMapper.selectByExample(example);
        if(creditReports ==null || creditReports.size() == 0)return null;

        return creditReports.get(0);
    }

    public CreditReport getSelectinigReport(Integer empId, String customerName, String customerIdCardNum,
                                            String customerMobile, String customerBankCard, Integer reportType){
        CreditReportExample example = new CreditReportExample();
        CreditReportExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdEqualTo(empId);
        criteria.andCustomerNameEqualTo(customerName);
        criteria.andCustomerIdCardNumEqualTo(customerIdCardNum);
        criteria.andCustomerMobileEqualTo(customerMobile);
        criteria.andReportTypeEqualTo(reportType);
        if(!StringUtils.isBlank(customerBankCard)){
            criteria.andCustomerBankCardEqualTo(customerBankCard);
        }
        criteria.andStatusEqualTo(CreditReportEnum.Status.ONGOING.getValue());
        example.setOrderByClause("FApplyTime DESC");
        List<CreditReport> creditReports = this.creditReportMapper.selectByExample(example);
        if(creditReports ==null || creditReports.size() == 0)return null;

        return creditReports.get(0);
    }
    /**
     * 获取客户列表
     * @param empId 员工ID
     * @param deptId    部门id
     * @param posision  审核人职位
     * @param querierInfo   搜索关键字
     * @param currentPage   当前页数
     * @param pageSize  当前列数
     * @return  Page<CreditReport>
     */
    public Page<CreditReport> findCustomer(Integer empId, Integer deptId, EmpEnums.Position posision, String querierInfo,
                                            Integer currentPage, Integer pageSize,Integer showMonth){
        PageHelper.startPage(currentPage, pageSize);//mybatis默认分页从1开始，返回时再扣1
        List<CreditReport> creditReports = this.creditReportMapper.findCustomer(empId, deptId, posision.getValue(), querierInfo,showMonth);

        PageInfo<CreditReport> CreditReportPageInfo = new PageInfo<>(creditReports);
        return new Page<>(pageSize, currentPage,
                CreditReportPageInfo.getList(), (int)CreditReportPageInfo.getTotal());
    }

    /**
     * 报告反馈
     * @param uuid    报告UUID码
     * @param isUseful  是否有用
     */
    public Boolean reportBack(String uuid, Boolean isUseful){
        CreditReport creditReport = new CreditReport();
        creditReport.setIsUseful(isUseful);
        creditReport.setIsFeedBacked(true);
        creditReport.setFeedBackTime(new Date());

        CreditReportExample example = new CreditReportExample();
        CreditReportExample.Criteria criteria = example.createCriteria();
        criteria.andUuidEqualTo(uuid);
        return this.creditReportMapper.updateByExampleSelective(creditReport, example) > 0;
    }

    /**
     * 新建征信报告
     * @param empId 员工ID
     * @param customerName  征信人姓名
     * @param customerIdCardNum 征信人身份证
     * @param customerMobile    征信人手机号
     * @param reportType    征信类型
     * @return  报告ID
     */
    public Integer createCreditReport(Integer empId,String customerName, String customerIdCardNum,
                                      String customerMobile, String customerBankCard, Integer reportType,
                                      String reportNum, String reportUUID){
        CreditReport creditReport = new CreditReport();
        creditReport.setEmpId(empId);
        creditReport.setCustomerName(customerName);
        creditReport.setCustomerIdCardNum(customerIdCardNum);
        creditReport.setCustomerMobile(customerMobile);
        creditReport.setCustomerBankCard(customerBankCard);
        creditReport.setReportType(reportType);
        creditReport.setUuid(reportUUID);

        creditReport.setReportNum(reportNum);
        creditReport.setApplyUserId(empId);
        creditReport.setApplyTime(new Date());
        creditReport.setStatus(CreditReportEnum.Status.ONGOING.getValue());
        creditReport.setIsReaded(false);
        creditReport.setIsFeedBacked(false);
        creditReport.setCreateTime(new Date());
        creditReport.setCreateUserId(empId);
        creditReport.setIsDelete(false);
        this.creditReportMapper.insertSelective(creditReport);
        return creditReport.getId();
    }

    /**
     * 删除同一业务员同一征信人同一类型失败的报告
     * @param empId 业务员ID
     * @param customerName  征信人姓名
     * @param customerIdCardNum 征信人身份证号
     * @param customerMobile    征信人手机号
     * @param customerBankCard  征信人银行卡号
     * @param reportType    征信报告类型
     */
    public void deleteFailReport(Integer empId,String customerName, String customerIdCardNum,
                                 String customerMobile, String customerBankCard, Integer reportType){
        CreditReport creditReport = new CreditReport();
        creditReport.setIsDelete(Boolean.TRUE);

        CreditReportExample example = new CreditReportExample();
        CreditReportExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdEqualTo(empId);
        criteria.andCustomerNameEqualTo(customerName);
        criteria.andCustomerIdCardNumEqualTo(customerIdCardNum);
        criteria.andCustomerMobileEqualTo(customerMobile);
        if(!StringUtils.isBlank(customerBankCard)){
            criteria.andCustomerBankCardEqualTo(customerBankCard);
        }
        criteria.andReportTypeEqualTo(reportType);
        criteria.andStatusEqualTo(CreditReportEnum.Status.FAIL.getValue());

        this.creditReportMapper.updateByExampleSelective(creditReport,example);
    }

    /**
     * 获取员工每日查询该征信报告次数
     * @param empId 员工ID
     * @return  Integer
     */
    public Integer getLimitDailCount(Integer empId,Integer reportType){
        return this.creditReportMapper.getLimitDailCount(empId,reportType);
    }

    /**
     * 获取员工一个时间段内获取该类型报告次数
     * @param empId 员工ID
     * @param reportType    报告类型
     * @param time  限制时间，单位分
     * @return  Integer
     */
    public Integer getLimitTime(Integer empId,Integer reportType,Integer time){
        return this.creditReportMapper.getLimitTime(empId,reportType,time);
    }

    public Boolean reportContentBack(String resultCode, String resultDesc,String reportNum
            ,String reportContent,Integer status){
        CreditReport creditReport = new CreditReport();
        if(!StringUtils.isBlank(resultCode)){
            creditReport.setReportCode(resultCode);
        }
        if(!StringUtils.isBlank(resultDesc)){
            creditReport.setReportDesc(resultDesc);
        }
        if(!StringUtils.isBlank(reportContent)){
            creditReport.setReportContent(reportContent);
        }
        creditReport.setResultNotifyTime(new Date());
        creditReport.setStatus(status);

        CreditReportExample example = new CreditReportExample();
        CreditReportExample.Criteria criteria = example.createCriteria();
        criteria.andReportNumEqualTo(reportNum);
        return this.creditReportMapper.updateByExampleSelective(creditReport,example) > 0;
    }

    public CreditReport getReportByReportNum(String reportNum){
        CreditReportExample example = new CreditReportExample();
        CreditReportExample.Criteria criteria = example.createCriteria();
        criteria.andReportNumEqualTo(reportNum);
        List<CreditReport> creditReports = this.creditReportMapper.selectByExample(example);
        if(creditReports ==null || creditReports.size() == 0)return null;

        return creditReports.get(0);
    }


    /**
     * 登录的员工获取征信报告时，校验该员工是否有查看权限
     * @param position  当前登录人职位
     * @param deptId    当前登录人部门ID
     * @param empId      数据库中报告发起人(员工)ID
     * @return  Integer
     */
    public Integer permissionsCheck(Integer position, Integer deptId, Integer empId){
        return this.creditReportMapper.permissionsCheck(position,deptId,empId);
    }

    /**
     * 获取所有查询中的报告
     * @return  List<CreditReport>
     */
    public List<CreditReport> findSelectingReport(){
        CreditReportExample example = new CreditReportExample();
        CreditReportExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(CreditReportEnum.Status.ONGOING.getValue());
        criteria.andReportNumIsNotNull();
        criteria.andCustomerIdCardNumIsNotNull();
        criteria.andCustomerMobileIsNotNull();
        criteria.andCustomerNameIsNotNull();
        return this.creditReportMapper.selectByExample(example);
    }

    /**
     * 根据报告ID修改报告为已删除
     * @param id    报告ID
     */
    public void deleteReport(Integer id){
        CreditReport creditReport = new CreditReport();
        creditReport.setId(id);
        creditReport.setIsDelete(Boolean.TRUE);
        this.creditReportMapper.updateByPrimaryKeySelective(creditReport);
    }
}
