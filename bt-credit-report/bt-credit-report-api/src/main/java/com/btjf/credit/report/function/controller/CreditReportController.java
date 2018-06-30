package com.btjf.credit.report.function.controller;

import com.alibaba.fastjson.JSONObject;
import com.btjf.application.components.page.AppPageHelper;
import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.common.utils.BeanUtil;
import com.btjf.credit.report.base.controller.CreditBaseController;
import com.btjf.credit.report.bo.*;
import com.btjf.credit.report.domain.CreditReportDomain;
import com.btjf.credit.report.enums.CreditReportEnum;
import com.btjf.credit.report.exception.CreditReportExcetion;
import com.btjf.credit.report.vo.*;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 征信报告相关接口
 * Created by zsw on 2017/6/17.
 *
 */
@Api(value = "btcredit", description = "备胎信用征信报告")
@RestController
@RequestMapping("/m/btcredit/creditReport")
public class CreditReportController extends CreditBaseController {

    private static final Logger logger = LoggerFactory.getLogger(CreditReportController.class);
    @Resource
    private CreditReportDomain creditReportDomain;

    //默认当前页
    private static final Integer initCurrentPage = 0;
    //默认当前列数
    private static final Integer initPageSize = 20;

    @ApiOperation("获取员工所有类型报告未读数")
    @RequestMapping(value = "/unReadReport", method = RequestMethod.GET)
    public XaResult<UnReadReportVo> getUnReadReport() throws CreditReportExcetion {

        try {
            Integer currentUserID = this.getCurrentUserID();
            UnReadReportBo unReadReport = creditReportDomain.getUnReadReport(currentUserID);

            UnReadReportVo unReadReportVo = new UnReadReportVo(unReadReport);
            return AppXaResultHelper.success(unReadReportVo);
        } catch (CreditReportExcetion e) {
            logger.error("获取员工所有类型报告未读数 异常"+e.getMessage(),e);
            return XaResult.error(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }
    }

    @ApiOperation("首页点击产品时 通过报告类型获取报告可查开关")
    @RequestMapping(value = "/mainPageEnabled", method = RequestMethod.GET)
    public XaResult getMainPageEnabled(@ApiParam("报告类型 字段名:reportType") Integer reportType) throws CreditReportExcetion {
        try {
            //入参校验
            validReportType(reportType);

            Integer currentUserID = this.getCurrentUserID();
            Boolean mainPageEnabled = creditReportDomain.getProductIsUseful(currentUserID, reportType);

            return AppXaResultHelper.success(mainPageEnabled);
        } catch (CreditReportExcetion e) {
            logger.error("首页点击产品时 通过报告类型获取报告可查开关"+e.getMessage(),e);
            return XaResult.error(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }
    }

    @ApiOperation("新建征信报告时 通过报告类型获取报告可查开关")
    @RequestMapping(value = "/createEnabled", method = RequestMethod.GET)
    public XaResult getCreateEnabled(@ApiParam("报告类型 字段名:reportType") Integer reportType) throws CreditReportExcetion{
        try {
            //入参校验
            validReportType(reportType);

            Integer currentUserID = this.getCurrentUserID();
            Boolean createEnabled = creditReportDomain.getCreateEnabled(currentUserID, reportType);

            return AppXaResultHelper.success(createEnabled);
        } catch (CreditReportExcetion e) {
            logger.error("新建征信报告时 通过报告类型获取报告可查开关"+e.getMessage(),e);
            return XaResult.error(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }
    }

    @ApiOperation("人信页点击征信报告产品时，判断报告是否可查")
    @RequestMapping(value = "/renXinEnabled", method = RequestMethod.GET)
    public XaResult getRenXinEnabled(@ApiParam("征信人名字 字段名:customerName") String customerName,
                                     @ApiParam("征信人身份证 字段名:customerIdCardNum") String customerIdCardNum,
                                     @ApiParam("征信人手机号 字段名:customerMobile") String customerMobile,
                                     @ApiParam("报告类型 字段名:reportType") Integer reportType) throws CreditReportExcetion{
        try {
            //参数校验
            validCreditInfo(customerName,customerIdCardNum,customerMobile,reportType);

            Integer currentUserID = this.getCurrentUserID();
            Boolean renXinEnabled = creditReportDomain.getCreateEnabled(currentUserID, reportType);
            //人信页面点击征信产品时，如果该员工查询过该征信人信息，则返回报告ID
            if(renXinEnabled){
                String uuid = creditReportDomain.getLastReport(currentUserID, customerName,
                        customerIdCardNum, customerMobile, reportType);
                return AppXaResultHelper.success(uuid);
            }

            return AppXaResultHelper.success();
        } catch (CreditReportExcetion e) {
            logger.error("人信页点击征信报告产品时，判断报告是否可查"+e.getMessage(),e);
            return AppXaResultHelper.success();
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }
    }


    @ApiOperation("我的查询列表")
    @RequestMapping(value = "/myReportList", method = RequestMethod.GET)
    public XaResult<List<MyReportVo>> findMyReportList(@ApiParam("报告类型,字段名:reportType") Integer reportType,
                                                       @ApiParam("每页显示数量,字段名:pageSize") Integer pageSize,
                                                       @ApiParam("当前页码,字段名:currentPage") Integer currentPage) throws CreditReportExcetion{
        if(pageSize == null){
            pageSize = initPageSize;
        }

        if(currentPage == null){
            currentPage = initCurrentPage;
        }
        try {
            //入参校验
            validReportType(reportType);

            Integer currentUserID = this.getCurrentUserID();

            Page page = AppPageHelper.appInit(currentPage, pageSize);

            Page<CreditReportBo> pageResult = creditReportDomain.findMyReportList(currentUserID, reportType, page);

            List<MyReportVo> myReportVos = BeanUtil.convertList(pageResult.getRows(), MyReportVo.class);

            return AppXaResultHelper.success(pageResult, myReportVos);
        } catch (CreditReportExcetion e) {
            logger.error("我的查询列表"+e.getMessage(),e);
            return XaResult.error(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }

    }

    @ApiOperation("获取员工查询报告列表")
    @RequestMapping(value = "/staffReportList", method = RequestMethod.GET)
    public XaResult<List<EmpCreditReportVo>> findStaffReportList(@ApiParam("报告类型,字段名:reportType") Integer reportType,
                                                                 @ApiParam("每页显示数量,字段名:pageSize") Integer pageSize,
                                                                 @ApiParam("当前页码,字段名:currentPage") Integer currentPage) throws CreditReportExcetion{
        if(pageSize == null){
            pageSize = initPageSize;
        }

        if(currentPage == null){
            currentPage = initCurrentPage;
        }

        try {
            //入参校验
            validReportType(reportType);

            Integer currentUserID = this.getCurrentUserID();

            Page page = AppPageHelper.appInit(currentPage, pageSize);

            Page<EmpCreditReportBo> pageResult = creditReportDomain.findStaffReportList(currentUserID, reportType, page);

            logger.info("员工查询报告列表返回信息："+ JSONObject.toJSONString(pageResult));

            Page<EmpCreditReportVo> empCreditReportVoPage = BeanUtil.convertPage(pageResult, EmpCreditReportVo.class);

            logger.info("员工查询报告列表返回转换后信息："+ JSONObject.toJSONString(empCreditReportVoPage));

            return AppXaResultHelper.success(pageResult, empCreditReportVoPage.getRows());
        } catch (CreditReportExcetion e) {
            logger.error("获取员工查询报告列表"+e.getMessage(),e);
            return XaResult.error(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }
    }

    @ApiOperation("搜索征信人报告列表")
    @RequestMapping(value = "/searchReportList", method = RequestMethod.GET)
    public XaResult<List<EmpCreditReportVo>> searchReportList(@ApiParam("报告类型,字段名:reportType") Integer reportType,
                                          @ApiParam("搜索关键字,字段名:queryStr") String queryStr,
                                          @ApiParam("每页显示数量,字段名:pageSize") Integer pageSize,
                                          @ApiParam("当前页码,字段名:currentPage") Integer currentPage)throws CreditReportExcetion {
        if(!StringUtils.isBlank(queryStr)){
            queryStr = queryStr.replaceAll("\\s*", "");
        }
        if(pageSize == null){
            pageSize = initPageSize;
        }

        if(currentPage == null){
            currentPage = initPageSize;
        }
        try {
            //入参校验
            validReportType(reportType);

            Integer currentUserID = this.getCurrentUserID();

            Page page = AppPageHelper.appInit(currentPage, pageSize);

            Page<EmpCreditReportBo> pageResult = creditReportDomain.searchReportList(currentUserID, queryStr, reportType, page);

            Page<EmpCreditReportVo> empCreditReportVoPage = BeanUtil.convertPage(pageResult, EmpCreditReportVo.class);

            return AppXaResultHelper.success(pageResult, empCreditReportVoPage.getRows());
        } catch (CreditReportExcetion e) {
            logger.error("搜索征信人报告列表"+e.getMessage(),e);
            return XaResult.error(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }

    }

    @ApiOperation("获取征信报告详情")
    @RequestMapping(value = "/reportDetails", method = RequestMethod.GET)
    public XaResult<ReportDetailsVo> getReportDetails(@ApiParam("报告UUID码,字段名:uuid") String uuid) throws CreditReportExcetion{
        if (StringUtils.isBlank(uuid)) {
            return XaResult.error("报告UUID不能为空");
        }

        ReportDetailsVo creditReportVo;
        try {
            Integer currentUserID = this.getCurrentUserID();
            CreditReportBo reportDetails = creditReportDomain.getReportDetails(currentUserID, uuid);

            if(reportDetails == null){
                XaResult.error("该征信报告详情不存在");
            }
            if(null == reportDetails.getStatus()){
                throw new CreditReportExcetion(CreditReportEnum.StatusException.ERROR_REPORT.getContent());
            }
            Integer status = reportDetails.getStatus();
            if(CreditReportEnum.Status.FAIL.getValue().equals(status)){
                return XaResult.success();
            }
            creditReportVo = new ReportDetailsVo(reportDetails);

            //报告详情页要求返回大数据报告是否可查判断，APP在做详情页查看大数据时需要
            Boolean isBigDataEnabled = Boolean.FALSE;
            try {
                isBigDataEnabled = creditReportDomain.getProductIsUseful(currentUserID, CreditReportEnum.Type.FULL.getValue());
            } catch (CreditReportExcetion e) {
                creditReportVo.setIsBigDataEnabled(Boolean.FALSE);
            }
            creditReportVo.setIsBigDataEnabled(isBigDataEnabled);

            return XaResult.success(creditReportVo);
        } catch (CreditReportExcetion e) {
            logger.error("获取征信报告详情"+e.getMessage(),e);
            return XaResult.error(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }

    }

    @ApiOperation("获取征信人最新大数据征信报告")
    @RequestMapping(value = "/newBigDataReport", method = RequestMethod.GET)
    public XaResult getNewBigDataReport(@ApiParam("征信人名字 字段名:customerName") String customerName,
                                            @ApiParam("征信人身份证 字段名:customerIdCardNum") String customerIdCardNum,
                                            @ApiParam("征信人手机号 字段名:customerMobile") String customerMobile) throws CreditReportExcetion{
        try {
            //参数校验
            CreditReportApplyBo creditReportApplyBo = new CreditReportApplyBo();
            creditReportApplyBo.setCustomerIdCardNum(customerIdCardNum);
            creditReportApplyBo.setCustomerName(customerName);
            creditReportApplyBo.setCustomerMobile(customerMobile);

            creditReportApplyBo = validCreditInfo(creditReportApplyBo);

            Integer currentUserID = this.getCurrentUserID();
            String uuid = creditReportDomain.getLastReport(currentUserID, creditReportApplyBo.getCustomerName(),
                    creditReportApplyBo.getCustomerIdCardNum(),creditReportApplyBo.getCustomerMobile(),CreditReportEnum.Type.FULL.getValue());

            //没有报告历史查询记录，APP跳转到新建页面
            if(StringUtils.isBlank(uuid)){
                return XaResult.success();
            }

            //跳转到详情页
            return XaResult.success(uuid);
        } catch (CreditReportExcetion e) {
            logger.error("获取征信人最新大数据征信报告"+e.getMessage(),e);
            return XaResult.error(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }
    }

    @ApiOperation("搜索已查询过征信的客户列表")
    @RequestMapping(value = "/seachCustomer", method = RequestMethod.GET)
    public XaResult<List<CustomerListVo>> seachCustomer(@ApiParam("关键字,字段名:queryStr")String queryStr,
                                       @ApiParam("每页显示数量,字段名:pageSize") Integer pageSize,
                                       @ApiParam("当前页码,字段名:currentPage") Integer currentPage) throws CreditReportExcetion{
        if(!StringUtils.isBlank(queryStr)){
            queryStr = queryStr.replaceAll("\\s*", "");
        }
        if(pageSize == null){
            pageSize = 20;
        }

        if(currentPage == null){
            currentPage = 0;
        }
        try {
            Integer currentUserID = this.getCurrentUserID();

            Page page = AppPageHelper.appInit(currentPage, pageSize);

            Page<CreditReportBo> pageResult = creditReportDomain.findCustomerList(currentUserID,queryStr, page);

            Page<CustomerListVo> customerListVoPage = BeanUtil.convertPage(pageResult, CustomerListVo.class);

            return AppXaResultHelper.success(pageResult, customerListVoPage.getRows());
        } catch (CreditReportExcetion e) {
            logger.error("搜索已查询过征信的客户列表"+e.getMessage(),e);
            return XaResult.error(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }
    }

    @ApiOperation("征信报告反馈")
    @RequestMapping(value = "/reportBack", method = RequestMethod.POST)
    public XaResult reportBack(@ApiParam("报告uuid码,字段名:uuid")String uuid,
                                    @ApiParam("是否有用,字段名:isUserful")Boolean isUserful) throws CreditReportExcetion{
        if(StringUtils.isBlank(uuid)){
            XaResult.error("报告UUID不能为空");
        }
        if(isUserful == null){
            XaResult.error("参数错误");
        }

        try {
            Boolean isBack = creditReportDomain.reportBack(uuid, isUserful);

            return XaResult.success(isBack);
        } catch (CreditReportExcetion e) {
            logger.error("征信报告反馈"+e.getMessage(),e);
            return XaResult.error(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }
    }

    @ApiOperation("新建征信报告")
    @RequestMapping(value = "/createCreditReport", method = RequestMethod.POST)
    public XaResult createCreditReport(@ApiParam("征信人名字 字段名:customerName") String customerName,
                                           @ApiParam("征信人身份证 字段名:customerIdCardNum") String customerIdCardNum,
                                           @ApiParam("征信人手机号 字段名:customerMobile") String customerMobile,
                                           @ApiParam("征信人银行卡号 字段名:customerBankCard") String customerBankCard,
                                           @ApiParam("报告类型 字段名:reportType") Integer reportType,
                                           @ApiParam("新建报告方式 字段名:requestType") Integer requestType) throws CreditReportExcetion{
        try {
            //参数校验
            validCreditInfo(customerName,customerIdCardNum,customerMobile,reportType);

            Integer currentUserID = this.getCurrentUserID();
            if(CreditReportEnum.Type.FULL.getValue().equals(reportType)){
                //获取大数据是否可查
                Boolean createEnabled = creditReportDomain.getCreateEnabled(currentUserID, CreditReportEnum.Type.FULL.getValue());

                Boolean isHaveBankCard = StringUtils.isBlank(customerBankCard);

                if((!isHaveBankCard && !createEnabled) || isHaveBankCard){
                    //提示语
                    String markWord = CreditReportEnum.StatusException.NO_SELECT_BIGDATA.getContent();
                    //银行卡为空时给出提示
                    if(isHaveBankCard){
                        markWord = CreditReportEnum.StatusException.NO_BANKCARD.getContent();
                    }
                    List<UsableReportBo> usableReports = creditReportDomain.findUsableReport();

                    if(usableReports == null || usableReports.size() == 0){
                        return XaResult.error(CreditReportEnum.StatusException.NO_SELECT.getContent());
                    }
                    //除去大数据报告
                    List<UsableReportBo> usableReportBos = removeBigData(usableReports);

                    List<CreateUsableReportVo> createUsableReportVos = BeanUtil.convertList(usableReportBos, CreateUsableReportVo.class);
                    return new XaResult(100,markWord,createUsableReportVos,null);
                }
            }

            String mobileIsValid = creditReportDomain.createCreditReport(currentUserID, customerName, customerIdCardNum,
                    customerMobile, customerBankCard, reportType, requestType);
            if(!StringUtils.isBlank(mobileIsValid) && !"SUCCESS".equals(mobileIsValid)){
                return new XaResult(101,mobileIsValid,null,null);
            }
            return XaResult.success();
        } catch (CreditReportExcetion e) {
            logger.error("新建征信报告"+e.getMessage(),e);
            return XaResult.error(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }
    }

    private List<UsableReportBo> removeBigData(List<UsableReportBo> list){

        for (int i = 0; i < list.size(); i++) {
            if(CreditReportEnum.Type.FULL.getValue().equals(list.get(i).getReportType())){
                list.remove(list.get(i));
                i--;
            }
        }
        return list;
    }

    @ApiOperation("获取所有可用的征信报告产品")
    @RequestMapping(value = "/usableReport", method = RequestMethod.GET)
    public XaResult<List<UsableReportVo>> findUsableReport() throws CreditReportExcetion{

        try {
            List<UsableReportBo> usableReport = creditReportDomain.findUsableReport();
            List<UsableReportVo> convert = UsableReportVo.convert(usableReport);

            return XaResult.success(convert);
        } catch (CreditReportExcetion e) {
            logger.error("获取所有可用的征信报告产品"+e.getMessage(),e);
            return XaResult.error(e.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return XaResult.error(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
        }
    }

    private void validReportType(Integer reportType){
        if(reportType == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NOT_NULL_REPORTTYPE.getContent());
        }
    }

    private CreditReportApplyBo validCreditInfo(CreditReportApplyBo creditReportApplyBo){
        String customerName = creditReportApplyBo.getCustomerName();
        if (StringUtils.isEmpty(customerName)) {
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NOT_NULL_CREDITNAME.getContent());
        }
        creditReportApplyBo.setCustomerName(customerName.replaceAll("\\s*", ""));

        String customerIdCardNum = creditReportApplyBo.getCustomerIdCardNum();
        if (StringUtils.isEmpty(customerIdCardNum)) {
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NOT_NULL_CREDITIDCARD.getContent());
        }
        creditReportApplyBo.setCustomerIdCardNum(customerIdCardNum.replaceAll("\\s*", ""));

        String customerMobile = creditReportApplyBo.getCustomerMobile();
        if (StringUtils.isEmpty(customerMobile)) {
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NOT_NULL_CREDITMOBILE.getContent());
        }
        creditReportApplyBo.setCustomerMobile(customerMobile.replaceAll("\\s*", ""));

        return creditReportApplyBo;
    }

    private void validCreditInfo(String customerName,String customerIdCardNum,String customerMobile,Integer reportType){
        CreditReportApplyBo creditReportApplyBo = new CreditReportApplyBo();
        creditReportApplyBo.setCustomerIdCardNum(customerIdCardNum);
        creditReportApplyBo.setCustomerName(customerName);
        creditReportApplyBo.setCustomerMobile(customerMobile);

        validCreditInfo(creditReportApplyBo);

        validReportType(reportType);
    }

}
