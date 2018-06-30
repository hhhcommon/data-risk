package com.btjf.credit.report.domain;

import com.alibaba.fastjson.JSON;
import com.btjf.business.organize.dept.bo.DeptBo;
import com.btjf.business.organize.dept.domain.DeptDomain;
import com.btjf.business.organize.emp.bo.EmpBo;
import com.btjf.business.organize.emp.domain.EmpDomain;
import com.btjf.common.page.Page;
import com.btjf.common.utils.BeanUtil;
import com.btjf.common.utils.DateUtil;
import com.btjf.credit.report.bo.CreditReportBo;
import com.btjf.credit.report.bo.EmpCreditReportBo;
import com.btjf.credit.report.bo.UnReadReportBo;
import com.btjf.credit.report.bo.UsableReportBo;
import com.btjf.credit.report.constant.ModuleConstant;
import com.btjf.credit.report.constant.NodeConstant;
import com.btjf.credit.report.decision.dock.bo.DecisionProductBo;
import com.btjf.credit.report.decision.dock.domain.DecisionDockDomain;
import com.btjf.credit.report.decision.dock.enums.DecisionDockEnum;
import com.btjf.credit.report.decision.dock.exception.DecisionDockException;
import com.btjf.credit.report.decision.dock.service.SecurityUtils;
import com.btjf.credit.report.enums.CreditReportEnum;
import com.btjf.credit.report.enums.InterfaceTypeEnum;
import com.btjf.credit.report.exception.CreditReportExcetion;
import com.btjf.credit.report.model.CreditReport;
import com.btjf.credit.report.pojo.EmpCreditReport;
import com.btjf.credit.report.pojo.UnReadReport;
import com.btjf.credit.report.pojo.UsableReport;
import com.btjf.credit.report.service.CreditReportService;
import com.btjf.file.domain.FileRecordDomain;
import com.google.common.collect.Lists;
import com.icar.finance.event.EventBasiceMessageProducer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/12
 * @time 下午9:02
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */
@Service
public class CreditReportNativeDomain implements CreditReportDomain{
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditReportNativeDomain.class);

    //限制员工每天可查5个不同客户
    @Value("${creditReport.lilmit.dail.count}")
    private  Integer limitDailCount ;//大数据报告每个员工每天限制查5个不同征信人

    //报告图标类型
    @Value("${creditReport.icon.type}")
    private String iconType ;

    //限制员工时间段内只能查询一位客户
    @Value("${creditReport.limit.time}")
    private  Integer limitTime;

    @Value("${decision.appkey}")
    private String appKey;

    @Resource
    private DecisionDockDomain decisionDockDomain;
    @Resource
    private CreditReportService creditReportService;

    @Resource
    private FileRecordDomain fileRecordDomain;
    
    @Value("${creditReport.icon.url}")
    private String iconUrl;

    @Value("${creditReport.expiry.dail}")
    private Integer reportExpiryDail;

    @Value("${creditReport.show.month}")
    private Integer showMonth;

    @Resource
    private EmpDomain empDomain;
    @Resource
    private DeptDomain deptDomain;

    @Resource
    private EventBasiceMessageProducer eventBasiceMessageProducer;

    //30分钟查询中报告有效期
    private static final Long validTime = 30L;

    /**
     * 获取员工所有类型报告未读数
     * @param empId 员工ID
     * @return UnReadReportBo
     */
    @Override
    public UnReadReportBo getUnReadReport(Integer empId) {
        Date startDate = new Date();
        UnReadReportBo result = new UnReadReportBo();
        List<UnReadReport> unReadReport = this.creditReportService.getUnReadReport(empId);
        if (unReadReport != null && unReadReport.size() > 0){
            for (UnReadReport report : unReadReport) {
                if(CreditReportEnum.Type.FULL.getValue().equals(report.getReportType())){//大数据报告
                    result.setBigDataUnReadNumber(report.getReportCount());
                }else if(CreditReportEnum.Type.CREDIT.getValue().equals(report.getReportType())){//信贷记录
                    result.setCreditUnReadNumber(report.getReportCount());
                }else if(CreditReportEnum.Type.BAD.getValue().equals(report.getReportType())){//不良记录
                    result.setBadUnReadNumber(report.getReportCount());
                }else if(CreditReportEnum.Type.ALIPAY.getValue().equals(report.getReportType())){//支付宝评测
                    result.setAliPayUnReadNumber(report.getReportCount());
                }
            }
        }
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.UNREAD_REPORT.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回" + JSON.toJSONString(result));
        return result;
    }

    /**
     * 根据用户，判断产品是否可用
     * @param empId      员工ID
     * @param reportType 报告类型
     * @return  如果决策系统开关关闭，仍需判断是否有历史查询记录，返回报告是否可用
     */
    @Override
    public Boolean getProductIsUseful(Integer empId, Integer reportType) {
        Date startDate = new Date();
        //获取决策系统产品开关
        Boolean enabled = this.getDecisionProductEnabled(reportType);
        if(enabled){
            return true;
        }
        //未查询到产品开启，则要获取该员工是否有历史查询记录
        Integer reportListCount = this.creditReportService.findReportListCount(empId, reportType);
        if(reportListCount == 0){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.SELECT_NO_ENABLED.getContent());
        }
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.MAIN_PAGE_ENABLED.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回" + JSON.toJSONString(Boolean.TRUE));
        return Boolean.TRUE;
    }

    /**
     * 点击新建报告时，返回报告是否可用
     * @param empId      员工ID
     * @param reportType 报告类型
     * @return Boolean
     */
    @Override
    public Boolean getCreateEnabled(Integer empId, Integer reportType) {
        Date startDate = new Date();
        //点击新建大数据报告时才对员工次数限制判断
        if((CreditReportEnum.Type.FULL.getValue()).equals(reportType)){
            //判断员工是否为每天查5个征信人，每3分钟查一次
            this.empLimitNum(empId,reportType);
        }

        Boolean isEnable = this.getDecisionProductEnabled(reportType);
        if(!isEnable){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.SELECT_NO_ENABLED.getContent());
        }
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.CREATE_ENABLED.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回" + JSON.toJSONString(Boolean.TRUE));
        return Boolean.TRUE;
    }

    /**
     * 判断员工是否为每天查5个征信人，每3分钟查一次
     * @param empId 员工ID
     * @param reportType    报告类型
     */
    private void empLimitNum(Integer empId, Integer reportType){
        //员工1天内限制查询5个不同客户
        Integer limitCount = creditReportService.getLimitDailCount(empId, reportType);
        if(limitCount >= limitDailCount){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.LIMIT_CUSTOMER_COUNT.getContent());
        }
        //员工3分钟内仅可查1个客户
        Integer count = creditReportService.getLimitTime(empId, reportType, limitTime);
        if(count > 0){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.LIMIT_SELECT_TIME.getContent());
        }
    }

    /**
     * 获取决策系统开关
     * @return  Boolean
     */
    private Boolean getDecisionProductEnabled(Integer reportType){
        List<DecisionProductBo> productList = decisionDockDomain.getProductList();
        if(productList != null && productList.size() > 0){
            for (DecisionProductBo decisionProductBo : productList) {
                if((decisionProductBo.getProductid()).equals(reportType)){//从决策系统获取到产品ID与需要查询的产品类型一致，说明该开关已开启
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 获取可用的征信报告列表
     * @return  List<UsableReportBo>
     */
    @Override
    public List<UsableReportBo> findUsableReport(){
        Date startDate = new Date();
        //获取决策系统可用产品列表
        List<DecisionProductBo> productList = decisionDockDomain.getProductList();
        if(productList == null || productList.size() == 0){
            return null;
        }

        List<UsableReport> usableReportList = Lists.newArrayList();

        for (DecisionProductBo decisionProductBo : productList) {
            if((CreditReportEnum.Type.FULL.getValue()).equals(decisionProductBo.getProductid())
                    || (CreditReportEnum.Type.CREDIT.getValue()).equals(decisionProductBo.getProductid())
                    || (CreditReportEnum.Type.BAD.getValue()).equals(decisionProductBo.getProductid())) {

                UsableReport usableReport = new UsableReport();

                CreditReportEnum.Type type = CreditReportEnum.Type.getByValue(decisionProductBo.getProductid());
                if(type == null){
                    throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_PRODUCT.getContent()+decisionProductBo.getProductid());
                }
                usableReport.setReportType(type.getValue());
                usableReport.setReportName(type.getContent());

                String publicSitePrefix = fileRecordDomain.getPublicSitePrefix();

                usableReport.setReportUrl(publicSitePrefix + iconUrl + type.getValue() + iconType);

                usableReportList.add(usableReport);
            }
        }

        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.USABLED_REPORT.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回" + JSON.toJSONString(usableReportList));
        return BeanUtil.convertList(usableReportList,UsableReportBo.class);
    }

    /**
     * 我的查询报告列表
     * @param empId       员工ID集合
     * @param reportType  报告类型
     * @param page   当前页
     * @return  Page<CreditReportBo>
     */
    @Override
    public Page<CreditReportBo> findMyReportList(Integer empId, Integer reportType, Page page) {
        Date startDate = new Date();

        Page<CreditReport> reportList = creditReportService.findReportListByEmpIdAndType( empId, reportType, page.getPage(), page.getRp());

        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.MYREPORT_LIST.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回" + JSON.toJSONString(reportList));
        Page<CreditReportBo> creditReportBoPage = BeanUtil.convertPage(reportList, CreditReportBo.class);

        List<CreditReportBo> rows = creditReportBoPage.getRows();
        if(rows != null && rows.size() > 0){
            //对征信人手机号身份证加密
            rows = dataEncryList(rows);
            creditReportBoPage.setRows(rows);
        }
        return creditReportBoPage;

    }

    /**
     * 获取员工查询列表，普通员工不显示员工查询列表
     * @param empId       员工ID
     * @param reportType  报告类型
     * @param page   当前页
     * @return  Page<EmpCreditReportBo>
     */
    @Override
    public Page<EmpCreditReportBo> findStaffReportList(Integer empId, Integer reportType, Page page) {
        Date startDate = new Date();
        //获取员工信息
        EmpBo empBo = empDomain.get(empId);

        if(empBo == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_EMP.getContent());
        }

        Integer deptId = empBo.getDeptID();
        if(deptId == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.EMP_NO_IN_DEPT.getContent());
        }

        Page<EmpCreditReport> myReportList = creditReportService.findSubEmpReport(empId, deptId, reportType, empBo.getPosition(), page.getPage(), page.getRp(),showMonth);

        List<EmpCreditReport> rows = myReportList.getRows();
        if(rows != null && rows.size() > 0){
            //对征信人手机号身份证加密
            rows = dataEncryEmp(rows);
            myReportList.setRows(rows);
        }

        Page<EmpCreditReportBo> empCreditReportBoPage = BeanUtil.convertPage(myReportList, EmpCreditReportBo.class);

        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.STAFF_REPORT_LIST.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回empCreditReportBoPage " + JSON.toJSONString(empCreditReportBoPage));


        return empCreditReportBoPage;
    }

    /**
     * 搜索征信报告列表
     * @param empId       员工ID
     * @param queryStr    关键字：根据姓名模糊匹配、根据身份证号全量匹配
     * @param reportType  报告类型
     * @param page   当前页
     * @return  Page<EmpCreditReportBo>
     */
    @Override
    public Page<EmpCreditReportBo> searchReportList(Integer empId, String queryStr, Integer reportType, Page page) {//征信人姓名模糊、身份证号全量匹配
        Date startDate = new Date();
        //获取员工信息
        EmpBo empBo = empDomain.get(empId);
        if(empBo == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_EMP.getContent());
        }

        DeptBo deptBo = deptDomain.get(empBo.getDeptID());
        if(deptBo == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.EMP_NO_IN_DEPT.getContent());
        }

        Page<EmpCreditReport> searchReportList = creditReportService.searchReportList(empId, deptBo.getID(), queryStr, reportType, empBo.getPosition(), page.getPage(), page.getRp(),showMonth);

        List<EmpCreditReport> rows = searchReportList.getRows();
        if(rows != null && rows.size() > 0){
            //对征信人手机号身份证加密
            dataEncryEmp(rows);
        }

        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.SEARCH_REPORT.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回" + JSON.toJSONString(searchReportList));
        return BeanUtil.convertPage(searchReportList, EmpCreditReportBo.class);
    }

    /**
     * 获取报告详情
     * @param empId 员工ID
     * @param uuid    t_CreditReport.UUID码
     * @return  CreditReportBo
     */
    @Override
    public CreditReportBo getReportDetails(Integer empId,String uuid) {
        Date startDate = new Date();
        CreditReport creditReport = creditReportService.getReportDetails(uuid);
        if(creditReport == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_REPORT_DETAIL.getContent());
        }

        //获取当前登录人信息
        EmpBo empBo = empDomain.get(empId);
        if(empBo == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_EMP.getContent());
        }
        //校验该员工是否有查看报告权限
        Integer havePermission = creditReportService.permissionsCheck(empBo.getPosition().getValue(), empBo.getDeptID(), creditReport.getEmpId());
        if(havePermission != 1){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_SEE_REPORT.getContent());
        }

        //判断该员工是否为报告申请人，是则修改已读、已读时间
        creditReportService.updateReportReaded(empId,creditReport.getCustomerIdCardNum(),creditReport.getReportType());

        //处理报告状态
        processReportStatus(creditReport);

        CreditReportBo creditReportBo = BeanUtil.convert(creditReport, CreditReportBo.class);

        String customerMobile = creditReportBo.getCustomerMobile();
        if(!StringUtils.isBlank(customerMobile)){
            creditReportBo.setCustomerMobileFull(customerMobile);
        }
        String customerIdCardNum = creditReportBo.getCustomerIdCardNum();
        if(!StringUtils.isBlank(customerIdCardNum)){
            creditReportBo.setCustomerIdCardNumFull(customerIdCardNum);
        }

        //对征信人手机号身份证加密
        creditReportBo = dataEncry(creditReportBo);

        //报告内容解密
        String reportContent = creditReportBo.getReportContent();
        if(!StringUtils.isBlank(reportContent)){
            try {
                LOGGER.info("报告内容解密前: "+reportContent+" ========appKey: "+appKey);
                String cecurityContent = SecurityUtils.decode(reportContent, appKey);
                LOGGER.info("报告内容解密后: "+cecurityContent);
                creditReportBo.setReportContent(cecurityContent);
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
                throw new CreditReportExcetion(CreditReportEnum.StatusException.RESOLVE_FAIL.getContent());
            }
        }

        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.REPORT_DETAIL.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回" + JSON.toJSONString(creditReportBo));
        return creditReportBo;
    }

    /**
     * 处理征信报告状态，包含查询中、查询失败、1月失效提示
     * @param creditReport  征信报告实体类
     */
    private void processReportStatus(CreditReport creditReport){
        Integer status = creditReport.getStatus();
        if(status == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.ERROR_REPORT.getContent());
        }
        Integer reportType = creditReport.getReportType();
        if(reportType == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.ERROR_REPORT.getContent());
        }
        if(CreditReportEnum.Status.ONGOING.getValue().equals(status)){//征信报告查询中
            CreditReportEnum.Type type = CreditReportEnum.Type.getByValue(reportType);
            if(type == null){
                throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_PRODUCT.getContent());
            }
            throw new CreditReportExcetion(type.getContent()+"正在查询中，请耐心等待！");
        }else if (CreditReportEnum.Status.FAIL.getValue().equals(status)){//征信报告查询失败
            CreditReportEnum.Type type = CreditReportEnum.Type.getByValue(reportType);
            if(type == null){
                throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_PRODUCT.getContent());
            }
            //失败报告不给提示，APP要求
            //throw new CreditReportExcetion(type.getContent()+"查询失败，是否重新提交查询？");
        }
        Date applyTime = creditReport.getApplyTime();
        if(applyTime == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.ERROR_REPORT.getContent());
        }

        //设置报告过期标记
        long compare = DateUtil.compare(applyTime,new Date(),  DateUtil.coefficient_D);
        if(compare > Long.valueOf(reportExpiryDail)){
            //超过限制日期，过期
            creditReport.setStatus(CreditReportEnum.Status.EXPIRE.getValue());
        }

    }

    /**
     * 获取客户列表
     * @param empId       员工ID
     * @param querierInfo 关键字
     * @param page   页
     * @return  Page<CreditReportBo>
     */
    @Override
    public Page<CreditReportBo> findCustomerList(Integer empId,String querierInfo, Page page) {
        Date startDate = new Date();
        //获取员工信息
        EmpBo empBo = empDomain.get(empId);
        if(empBo == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_EMP.getContent());
        }

        DeptBo deptBo = deptDomain.get(empBo.getDeptID());
        if(deptBo == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.EMP_NO_IN_DEPT.getContent());
        }

        Page<CreditReport> customerList = creditReportService.findCustomer(empId, deptBo.getID(), empBo.getPosition(), querierInfo,page.getPage(), page.getRp(),showMonth);

        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.CUSTOMER_LIST.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回" + JSON.toJSONString(customerList));
        Page<CreditReportBo> creditReportBoPage = BeanUtil.convertPage(customerList, CreditReportBo.class);

        List<CreditReportBo> rows = creditReportBoPage.getRows();
        if(rows != null && rows.size() > 0){
            //对征信人手机号身份证加密
            dataEncryList(rows);
        }
        return creditReportBoPage;
    }

    /**
     * 获取最新报告
     * @param empId             员工ID
     * @param customerName      征信人姓名
     * @param customerIdCardNum 征信人身份证
     * @param customerMobile    征信人手机号
     * @param reportType        报告类型
     * @return  Integer
     */
    @Override
    public String getLastReport(Integer empId, String customerName, String customerIdCardNum,
                                        String customerMobile, Integer reportType) {
        Date startDate = new Date();
        //判断数据库中是否有数据，及查询状态
        this.isHaveReportInData(empId, customerName,customerIdCardNum,customerMobile, reportType);

        //员工ID，身份证获取数据中大数据报告
        CreditReport lastBigDataReport = creditReportService.getLastReport(empId,customerName, customerIdCardNum,customerMobile, reportType);
        //对征信人手机号身份证加密
//        dataEncry(lastBigDataReport);

        if(lastBigDataReport == null || CreditReportEnum.Status.FAIL.getValue().equals(lastBigDataReport.getStatus())){
            return null;
        }
        if(CreditReportEnum.Status.ONGOING.getValue().equals(lastBigDataReport.getStatus())){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.REPORT_SELECTGING.getContent());
        }
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.LAST_REPORT.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回" + JSON.toJSONString(lastBigDataReport.getId()));

        return lastBigDataReport.getUuid();
    }

    @Transactional(rollbackFor = Exception.class,readOnly = false)
    @Override
    public String createCreditReport(Integer empId, String customerName, String customerIdCardNum,
                                                     String customerMobile, String customerBankCard, Integer reportType,
                                   Integer requestType) {
        Date startDate = new Date();
        //判断数据库中是否有数据，及查询状态
        CreditReport newReport = creditReportService.getSelectinigReport(empId, customerName,customerIdCardNum,
                customerMobile,customerBankCard, reportType);

        //处理查询中的报告
        processSelectingReport(newReport);

        //请求类型，如果不传默认为点击立即查询，还是会去验证手机号，兼容老版本
        if(reportType == null){
            reportType = CreditReportEnum.SelectReportType.IMMEDIATELY_QUERY.getValue();
        }
        //判断传入数据是否一致
        String dataFit = this.isDataFit(customerName, customerIdCardNum, customerMobile, customerBankCard, reportType, requestType);

        if(!StringUtils.isBlank(dataFit) && !"SUCCESS".equals(dataFit)){
            return dataFit;
        }
        //判断报告类型,对接决策系统获取报告编号
        String reportNum = this.getReportNum(customerName,customerIdCardNum,customerMobile,customerBankCard,reportType);

        //删除同业务员同征信人同类型失败的报告
        creditReportService.deleteFailReport(empId,customerName,customerIdCardNum,customerMobile,customerBankCard,reportType);

        //新增报告生成UUID
        String reportUUID = UUID.randomUUID().toString();
        //返回报告ID
        Integer id = creditReportService.createCreditReport(empId,customerName,customerIdCardNum,customerMobile,customerBankCard,reportType,reportNum,reportUUID);
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.CREATE_REPORT.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回" + JSON.toJSONString(id));

        return dataFit;
    }

    private void processSelectingReport(CreditReport newReport){
        if(newReport != null){
            if((CreditReportEnum.Status.ONGOING.getValue()).equals(newReport.getStatus())){//查询征信人是否在查询中
                CreditReportEnum.Type type = CreditReportEnum.Type.getByValue(newReport.getReportType());
                if(type == null){
                    throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_PRODUCT.getContent()+newReport.getReportType());
                }

                throw new CreditReportExcetion(type.getContent()+"正在查询中，请耐心等待");
            }
        }
    }
    /**
     * 判断数据库是否有数据
     * 判断查询状态
     * @param empId 员工ID
     * @param customerIdCardNum 身份证
     * @param reportType    报告类型
     */
    private void isHaveReportInData(Integer empId, String customerName, String customerIdCardNum,
                                    String customerMobile, Integer reportType) {
        CreditReport newReport = creditReportService.getLastReport(empId, customerName,customerIdCardNum,customerMobile, reportType);

        //处理查询中的报告
        processSelectingReport(newReport);
    }

    /**
     * 判断传入数据是否一致
     * @param customerName  征信人姓名
     * @param customerIdCardNum 征信人身份证
     * @param customerMobile    征信人手机号
     * @param customerBankCard  征信人银行卡
     * @param requestType 请求报告方式，1：手机号信息不一致点击继续提交；2：直接点击立即提交；
     */
    private String isDataFit(String customerName,String customerIdCardNum,String customerMobile,
                           String customerBankCard, Integer reportType, Integer requestType){
        String mobileIsValid = "SUCCESS";
        try {

            if((CreditReportEnum.Type.FULL.getValue()).equals(reportType)){
                //校验银行卡卡bin
                String checkBankCard = decisionDockDomain.checkBankCard(customerBankCard);
                if(DecisionDockEnum.BankCardBinCode.INVALID_BANKCARD.getValue().equals(checkBankCard)){
                    throw new CreditReportExcetion(CreditReportEnum.StatusException.BANKCARD_ERROR.getContent());
                }else if (DecisionDockEnum.BankCardBinCode.DEBIT_CARD.getValue().equals(checkBankCard)){
                    throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_DEBIT_CARD.getContent());
                }
                //姓名、身份证、银行卡是否一致
                String bankCardRealCheck = decisionDockDomain.bankCardRealCheck(customerName, customerIdCardNum, customerBankCard);
                if(!DecisionDockEnum.ThreeElementCheckCode.NAME_CHECK_SAME.getValue().equals(bankCardRealCheck)){
                    CreditReportEnum.BankCardRealCheckCode bankCardRealCheckCode = CreditReportEnum.BankCardRealCheckCode.getByValue(Integer.valueOf(bankCardRealCheck));
                    if(bankCardRealCheckCode != null){
                        throw new CreditReportExcetion(bankCardRealCheckCode.getContent());
                    }else{
                        throw new CreditReportExcetion(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
                    }
                }
            }
            //姓名、身份证是否一致
            String realNameCheck = decisionDockDomain.realNameCheck(customerName, customerIdCardNum);
            if(!DecisionDockEnum.ThreeElementCheckCode.NAME_CHECK_SAME.getValue().equals(realNameCheck)){
                CreditReportEnum.RealNameCheck realNameCheck1 = CreditReportEnum.RealNameCheck.getByValue(Integer.valueOf(realNameCheck));
                if(realNameCheck1 != null){
                    throw new CreditReportExcetion(realNameCheck1.getContent());
                }else{
                    throw new CreditReportExcetion(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
                }
            }
            //姓名、身份证、手机号是否一致
            String mobileRealCheck = decisionDockDomain.mobileRealCheck(customerName, customerIdCardNum, customerMobile);
            if((DecisionDockEnum.ThreeElementCheckCode.NAME_CHECK_NOSAME.getValue().equals(mobileRealCheck)
                    || DecisionDockEnum.ThreeElementCheckCode.NAME_CHECK_NORESULT.getValue().equals(mobileRealCheck))
                    && Integer.valueOf(CreditReportEnum.SelectReportType.IMMEDIATELY_QUERY.getValue()).equals(requestType)){//手机号不一致并且为点击立即提交才返回不一致信息
                CreditReportEnum.MobileRealCheck mobileRealCheck1 = CreditReportEnum.MobileRealCheck.getByValue(Integer.valueOf(mobileRealCheck));
                if(mobileRealCheck1 != null){
                    mobileIsValid = mobileRealCheck1.getContent();
                }else{
                    mobileIsValid = CreditReportEnum.StatusException.OTHER_ERROR.getContent();
                }
            }
        } catch (DecisionDockException decisionDockException) {
            DecisionDockEnum.ResultCode resultCode = DecisionDockEnum.ResultCode.getByValue(decisionDockException.getMessage());
            if(resultCode == null){
                throw new CreditReportExcetion(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
            }
            throw new CreditReportExcetion(CreditReportEnum.StatusException.ERROR_REPORT.getContent());
        }
        return mobileIsValid;
    }

    /**
     * 通过征信信息,对接决策系统获取报告编号
     * @param customerName  征信人姓名
     * @param customerIdCardNum 征信人身份证
     * @param customerMobile    征信人手机号
     * @param customerBankCard  征信人银行卡
     * @param reportType    报告类型
     * @return  String
     */
    private String getReportNum(String customerName, String customerIdCardNum,
                                String customerMobile, String customerBankCard, Integer reportType){
        Date startDate = new Date();
        String reportNum = null;
        //判断报告类型,对接决策系统获取报告编号
        if((CreditReportEnum.Type.FULL.getValue()).equals(reportType)){
            reportNum = decisionDockDomain.bigDataReportNum(customerName, customerIdCardNum, customerBankCard, customerMobile);
        }else if ((CreditReportEnum.Type.ALIPAY.getValue()).equals(reportType)){
            reportNum = decisionDockDomain.aliPayReportNum(customerName, customerIdCardNum,customerMobile);
        }else if ((CreditReportEnum.Type.BAD.getValue()).equals(reportType)){
            reportNum = decisionDockDomain.badReportNum(customerName, customerIdCardNum,customerMobile);
        }else if ((CreditReportEnum.Type.CREDIT.getValue()).equals(reportType)){
            reportNum = decisionDockDomain.creditRecordReportNum(customerName,customerIdCardNum,customerMobile);
        }
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.GET_REPORT_NUM.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回" + JSON.toJSONString(reportNum));
        return reportNum;
    }

    /**
     * 决策系统报告回调
     * @param resultCode    报告回调结果编码
     * @param resultDesc    报告回调结果描述
     * @param reportNum     报告编号
     * @param reportContent 报告内容
     * @return  CreditReport
     */
    @Transactional(rollbackFor = Exception.class,readOnly = false)
    @Override
    public CreditReportBo reportContentBack(String resultCode, String resultDesc,String reportNum,String reportContent){

        CreditReport creditReport = creditReportService.getReportByReportNum(reportNum);
        if(creditReport == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_REPORT_DETAIL.getContent());
        }

        //如果报告是查询成的，直接返回成功
        Integer reportStatus = creditReport.getStatus();
        if(reportStatus == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_REPORT_DETAIL.getContent());
        }
        if(CreditReportEnum.Status.SUCCESS.getValue().equals(reportStatus)){
            return null;
        }
        //设置报告回调状态
        Integer status;
        if(DecisionDockEnum.ResultCode.SUCCESS.getValue().equals(resultCode)){
            status = CreditReportEnum.Status.SUCCESS.getValue();
        }else{
            status = CreditReportEnum.Status.FAIL.getValue();
        }

        creditReportService.reportContentBack(resultCode,resultDesc,reportNum,reportContent,status);
        return BeanUtil.convert(creditReport,CreditReportBo.class);
    }

    /**
     * 获取查询中的报告
     * @return  List<CreditReportBo>
     */
    @Override
    public List<CreditReportBo> findSelectingReport() {
        List<CreditReport> selectingReport = creditReportService.findSelectingReport();
        if(selectingReport == null || selectingReport.size() == 0){
            return null;
        }

        return BeanUtil.convertList(selectingReport,CreditReportBo.class);
    }

    /**
     * 获取大数据征信报告内容
     * @param name     征信人姓名
     * @param idCard   征信人身份证号
     * @param mobile   征信人手机号
     * @param reportNo 报告编号
     * @return  String
     */
    @Override
    public String bigDataReportContent(String name, String idCard, String mobile, String reportNo) {
        return decisionDockDomain.bigDataReportContent(name,idCard,mobile,reportNo);
    }

    /**
     * 获取支付宝征信报告内容
     * @param name     征信人姓名
     * @param idCard   征信人身份证号
     * @param mobile   征信人手机号
     * @param reportNo 报告编号
     * @return  String
     */
    @Override
    public String aliPayReportContent(String name, String idCard, String mobile, String reportNo) {
        return decisionDockDomain.aliPayReportContent(name,idCard,mobile,reportNo);
    }

    /**
     * 获取不良记录征信报告内容
     * @param name     征信人姓名
     * @param idCard   征信人身份证号
     * @param mobile   征信人手机号
     * @param reportNo 报告编号
     * @return  String
     */
    @Override
    public String badReportContent(String name, String idCard, String mobile, String reportNo) {
        return decisionDockDomain.badReportContent(name,idCard,mobile,reportNo);
    }

    /**
     * 获取信贷征信报告内容
     * @param name     征信人姓名
     * @param idCard   征信人身份证号
     * @param mobile   征信人手机号
     * @param reportNo 报告编号
     * @return  String
     */
    @Override
    public String creditRecordReportContent(String name, String idCard, String mobile, String reportNo) {
        return decisionDockDomain.creditRecordReportContent(name,idCard,mobile,reportNo);
    }

    @Override
    public void inQueryDataSync() {
        //获取所有查询中的报告
        List<CreditReportBo> selectingReport = this.findSelectingReport();

        if(selectingReport != null && selectingReport.size() > 0){

            for (CreditReportBo creditReportBo : selectingReport) {
                Date applyTime = creditReportBo.getApplyTime();
                if(applyTime == null){
                    LOGGER.info(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
                    continue;
                }
                //判断报告是否已超时
                Boolean isOutTimeRange = outTimeRange(applyTime, validTime);
                if(isOutTimeRange){
                    //超出报告查询有效期，设置报告为查询失败
                    this.reportContentBack(null, null,creditReportBo.getReportNum(),null);
                }else{
                    String customerName = creditReportBo.getCustomerName();
                    String customerIdCardNum = creditReportBo.getCustomerIdCardNum();
                    String customerMobile = creditReportBo.getCustomerMobile();
                    String reportNum = creditReportBo.getReportNum();
                    //对直接请求决策系统获取报告内容参数校验
                    try {
                        VerifyParam(customerName,customerIdCardNum,customerMobile,reportNum);
                    } catch (CreditReportExcetion e) {
                        CreditReportEnum.StatusException value = CreditReportEnum.StatusException.getByValue(Integer.valueOf(e.getMessage()));
                        if(value != null){
                            LOGGER.info(value.getContent());
                            continue;
                        }else{
                            LOGGER.info(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
                        }
                    }

                    Integer reportType = creditReportBo.getReportType();
                    if(reportType == null){
                        LOGGER.info(CreditReportEnum.StatusException.OTHER_ERROR.getContent());
                        continue;
                    }
                    //获取报告内容
                    String reportContent = getReportContent(customerName,customerIdCardNum,customerMobile
                            ,reportNum,reportType);
                    if(reportContent == null){
                        continue;
                    }
                    //插入报告内容
                    this.reportContentBack("0000", "成功", reportNum, reportContent);
                    try {
                        //配置节点,获取报告内容成功发送事件消息
                        eventBasiceMessageProducer.send(ModuleConstant.BTCREDITREPORT_CODE, getReportNode(reportType), creditReportBo.getId());
                    } catch (Exception e) {
                        LOGGER.info("=========定时获取征信报告内容，发送事件消息失败======",e);
                    }
                }
                LOGGER.info("======征信报告定时任务，主动请求报告编号为 "+creditReportBo.getReportNum()+"======");
            }
            LOGGER.info("======征信报告定时任务，共完成 "+selectingReport.size()+" 条任务======");
        }else{
            LOGGER.info("======征信报告定时任务，没有需要执行的任务======");
        }
    }

    /**
     * 根据报告类型获取事件节点
     * @param reportType    报告类型
     * @return  String
     */
    private String getReportNode(Integer reportType){
        String nodeCode = null;
        if(CreditReportEnum.Type.FULL.getValue().equals(reportType)){
            nodeCode = NodeConstant.BTCREDIT_BIGDATA_CODE;

        }else if (CreditReportEnum.Type.CREDIT.getValue().equals(reportType)){
            nodeCode = NodeConstant.BTCREDIT_CREDIT_CODE;

        }else if (CreditReportEnum.Type.BAD.getValue().equals(reportType)){
            nodeCode = NodeConstant.BTCREDIT_BAD_CODE;

        }else if (CreditReportEnum.Type.ALIPAY.getValue().equals(reportType)){
            nodeCode = NodeConstant.BTCREDIT_ALIPAY_CODE;
        }
        return nodeCode;
    }
    private String getReportContent(String customerName,String customerIdCardNum,String customerMobile
            ,String reportNum,Integer reportType){
        String reportContent = null;
        if(CreditReportEnum.Type.FULL.getValue().equals(reportType)){
            reportContent = this.bigDataReportContent(customerName,
                    customerIdCardNum, customerMobile, reportNum);

        }else if (CreditReportEnum.Type.CREDIT.getValue().equals(reportType)){
            reportContent = this.creditRecordReportContent(customerName,
                    customerIdCardNum, customerMobile, reportNum);

        }else if (CreditReportEnum.Type.BAD.getValue().equals(reportType)){
            reportContent = this.badReportContent(customerName,
                    customerIdCardNum, customerMobile, reportNum);

        }else if (CreditReportEnum.Type.ALIPAY.getValue().equals(reportType)){
            reportContent = this.aliPayReportContent(customerName,
                    customerIdCardNum, customerMobile, reportNum);
        }
        return reportContent;
    }

    /**
     * 参数校验
     * @param customerName  征信人姓名
     * @param customerIdCardNum 征信人身份证号
     * @param customerMobile    征信人手机号
     * @param reportNum 报告编号
     */
    private void VerifyParam(String customerName,String customerIdCardNum,String customerMobile
            ,String reportNum){

        if(StringUtils.isBlank(customerName)){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NOT_NULL_CREDITNAME.getValue());
        }

        if(StringUtils.isBlank(customerIdCardNum)){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NOT_NULL_CREDITIDCARD.getValue());
        }

        if(StringUtils.isBlank(customerMobile)){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NOT_NULL_CREDITMOBILE.getValue());
        }

        if(StringUtils.isBlank(reportNum)){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NOT_NULL_REPORTNUM.getValue());
        }
    }

    /**
     * @param applyTime 报告生成时间
     * @param intervalTime  设定报告超出时间，单位：分
     * @return  Boolean
     */
    private Boolean outTimeRange(Date applyTime,Long intervalTime){

        long compare = DateUtil.compare(applyTime, new Date(), DateUtil.coefficient_m);
        //超出30分钟
        if(compare > intervalTime){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 报告反馈
     * @param uuid       t_CreditReport.uuid码
     * @param isUseful 报告是否有用
     * @return  Boolean
     */
    @Transactional(rollbackFor = Exception.class,readOnly = false)
    @Override
    public Boolean reportBack(String uuid, Boolean isUseful) {
        Date startDate = new Date();
        CreditReport creditReport = creditReportService.getReportDetails(uuid);
        if(creditReport == null){
            throw new CreditReportExcetion(CreditReportEnum.StatusException.NO_REPORT_DETAIL.getContent());
        }
        Boolean isBack = creditReportService.reportBack(uuid, isUseful);

        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("==" + InterfaceTypeEnum.REPORT_BACK.getContent() + "==" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法共耗时：" + useTime + "==返回" + JSON.toJSONString(isBack));

        return isBack;
    }

    /**
     * 数据加密
     * @param creditReport  征信报告对象
     * @return  CreditReportBo
     */
    private CreditReportBo dataEncry(CreditReportBo creditReport){
        if(creditReport == null){
            return null;
        }
        String customerMobile = creditReport.getCustomerMobile();
        if(!StringUtils.isBlank(customerMobile) && customerMobile.length() > 3){
            creditReport.setCustomerMobile(customerMobile.substring(0,3)+"****"+ customerMobile.substring(customerMobile.length()-3,customerMobile.length()));
        }
        String customerIdCardNum = creditReport.getCustomerIdCardNum();
        if(!StringUtils.isBlank(customerIdCardNum) && customerIdCardNum.length() > 4){
            creditReport.setCustomerIdCardNum(customerIdCardNum.substring(0,3)+"********"+customerIdCardNum.substring(customerIdCardNum.length()-4,customerIdCardNum.length()));
        }
        return creditReport;
    }

    /**
     * 数据加密
     * @param creditReports 征信报告对象集合
     * @return  List<CreditReportBo>
     */
    private List<CreditReportBo> dataEncryList(List<CreditReportBo> creditReports){
        if(creditReports == null || creditReports.size() == 0){
            return null;
        }
        for (CreditReportBo creditReport : creditReports) {
            String customerMobile = creditReport.getCustomerMobile();
            creditReport.setCustomerMobileFull(customerMobile);

            if(!StringUtils.isBlank(customerMobile) && customerMobile.length() > 3){
                creditReport.setCustomerMobile(customerMobile.substring(0,3)+"****"+ customerMobile.substring(customerMobile.length()-3,customerMobile.length()));
            }

            String customerIdCardNum = creditReport.getCustomerIdCardNum();
            creditReport.setCustomerIdCardNumFull(customerIdCardNum);

            if(!StringUtils.isBlank(customerIdCardNum) && customerIdCardNum.length() > 4){
                creditReport.setCustomerIdCardNum(customerIdCardNum.substring(0,3)+"********"+customerIdCardNum.substring(customerIdCardNum.length()-4,customerIdCardNum.length()));
            }
        }
        return creditReports;
    }

    /**
     * 数据加密
     * @param empCreditReports  员工报告对象集合
     * @return  List<EmpCreditReport>
     */
    private List<EmpCreditReport> dataEncryEmp(List<EmpCreditReport> empCreditReports){
        if(empCreditReports == null || empCreditReports.size() == 0){
            return null;
        }
        for (EmpCreditReport creditReport : empCreditReports) {
            String customerMobile = creditReport.getCustomerMobile();
            if(!StringUtils.isBlank(customerMobile) && customerMobile.length() > 3){
                creditReport.setCustomerMobile(customerMobile.substring(0,3)+"****"+ customerMobile.substring(customerMobile.length()-3,customerMobile.length()));
            }
            String customerIdCardNum = creditReport.getCustomerIdCardNum();
            if(!StringUtils.isBlank(customerIdCardNum) && customerIdCardNum.length() > 4){
                creditReport.setCustomerIdCardNum(customerIdCardNum.substring(0,3)+"********"+customerIdCardNum.substring(customerIdCardNum.length()-4,customerIdCardNum.length()));
            }
        }
        return empCreditReports;
    }

}
