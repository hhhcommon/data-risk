package com.btjf.credit.report.domain;

import com.btjf.common.page.Page;
import com.btjf.credit.report.bo.*;

import java.util.List;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/12
 * @time 下午9:01
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */
public interface CreditReportDomain {

    /**
     * 首页大数据、信贷、不良记录、支付宝评测
     *报告未读数统计
     * @param empId 员工ID
     * @return
     */
    UnReadReportBo getUnReadReport(Integer empId);

    /**
     * 根据用户，判断产品是否可用
     * 对接数据决策系统，报告类型获取报告可查开关
     * @param reportType    报告类型
     * @param empId    员工ID
     * @return
     */
    Boolean getProductIsUseful(Integer empId, Integer reportType);

    /**
     * 新建征信报告时
     * 对接数据决策系统，报告类型获取报告可查开关
     *@param empId  员工ID
     * @param reportType    报告类型
     * @return
     */
    Boolean getCreateEnabled(Integer empId,Integer reportType);

//    /**
//     * 人信页点击征信报告产品时，判断报告是否可查
//     * @param empId 员工ID
//     * @param reportType    报告类型
//     * @return
//     */
//    Boolean getRenXinEnabled(Integer empId,Integer reportType);

//    /**
//     * 对接数据决策系统，手机号获取所有报告可查开关
//     * @param userName  征信人姓名
//     * @param userPhone 征信人手机号
//     * @param userIdCard    征信人身份证
//     * @return
//     */
//    ReportEnabledBo getReportsEnabledByMobile(String userName, String userPhone, String userIdCard);

    /**
     * 获取我的查询报告列表
     * @param empId 员工ID集合
     * @param reportType    报告类型
     * @return
     */
    Page<CreditReportBo> findMyReportList(Integer empId, Integer reportType, Page page);

    /**
     * 获取员工查询报告列表
     * @param empId 员工ID
     * @param reportType    报告类型
     * @return
     */
    Page<EmpCreditReportBo> findStaffReportList(Integer empId, Integer reportType, Page page);

    /**
     * 搜索征信人报告列表
     * @param empId 员工ID
     * @param queryStr  关键字：根据姓名模糊匹配、根据身份证号全量匹配
     * @param reportType    报告类型
     * @return
     */
    Page<EmpCreditReportBo> searchReportList(Integer empId, String queryStr, Integer reportType, Page page);

    /**
     * 获取征信报告详情
     * @param empId 员工ID
     * @param uuid    t_CreditReport.uuid码
     * @return
     */
    CreditReportBo getReportDetails(Integer empId, String uuid);

    /**
     * 获取征信人最新征信报告
     * @param empId 员工ID
     * @param customerName  征信人姓名
     * @param customerIdCardNum     征信人身份证
     * @param customerMobile    征信人手机号
     * @param reportType    报告类型
     * @return  uuid
     */
    String getLastReport(Integer empId, String customerName, String customerIdCardNum,
                                 String customerMobile, Integer reportType);


    /**
     * 搜索已查询过征信的客户列表
     * @param empId 员工ID
     * @param querierInfo   关键字
     * @param page   页
     * @return
     */
    Page<CreditReportBo> findCustomerList(Integer empId,String querierInfo, Page page);

    /**
     * 征信报告反馈
     * @param uuid    t_CreditReport.uuid码
     * @param isUseful  报告是否有用
     */
    Boolean reportBack(String uuid , Boolean isUseful);

    /**
     * 新建征信报告
     * @param empId 员工ID
     * @param customerName  征信人姓名
     * @param customerIdCardNum 征信人身份证
     * @param customerMobile    征信人手机号
     * @param customerBankCard  征信人银行卡
     * @param reportType    征信类型
     * @param requestType 请求报告方式，1：手机号信息不一致点击继续提交；2：直接点击立即提交；
     */
    String createCreditReport(Integer empId, String customerName, String customerIdCardNum,
                                              String customerMobile, String customerBankCard, Integer reportType,
                            Integer requestType);

    /**
     * 获取所有可用的征信报告产品
     * @return
     */
    List<UsableReportBo> findUsableReport();


    /**
     * 提供给决策回调接口
     * @param resultCode    报告回调结果编码
     * @param resultDesc    报告回调结果描述
     * @param reportNum     报告编号
     * @param reportContent 报告内容
     * @return
     */
    CreditReportBo reportContentBack(String resultCode, String resultDesc,String reportNum,String reportContent);

    /**
     * 获取所有查询中的报告
     * @return
     */
    List<CreditReportBo> findSelectingReport();

    /**
     * 获取大数据报告内容
     * @param name  征信人姓名
     * @param idCard    征信人身份证号
     * @param mobile    征信人手机号
     * @param reportNo  报告编号
     * @return
     */
    String bigDataReportContent(String name, String idCard, String mobile, String reportNo);

    /**
     * 获取支付宝征信报告内容
     * @param name  征信人姓名
     * @param idCard    征信人身份证号
     * @param mobile    征信人手机号
     * @param reportNo  报告编号
     * @return
     */
    String aliPayReportContent(String name, String idCard, String mobile, String reportNo);

    /**
     * 获取不良记录报告内容
     * @param name  征信人姓名
     * @param idCard    征信人身份证号
     * @param mobile    征信人手机号
     * @param reportNo  报告编号
     * @return
     */
    String badReportContent(String name, String idCard, String mobile, String reportNo);

    /**
     * 获取征信报告内容
     * @param name  征信人姓名
     * @param idCard    征信人身份证号
     * @param mobile    征信人手机号
     * @param reportNo  报告编号
     * @return
     */
    String creditRecordReportContent(String name, String idCard, String mobile, String reportNo);

    /**
     * 定时任务
     */
    void inQueryDataSync();
}
