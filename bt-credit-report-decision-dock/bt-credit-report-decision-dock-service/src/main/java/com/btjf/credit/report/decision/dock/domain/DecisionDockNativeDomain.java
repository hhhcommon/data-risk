package com.btjf.credit.report.decision.dock.domain;

import com.alibaba.fastjson.JSON;
import com.btjf.common.utils.BeanUtil;
import com.btjf.credit.report.decision.dock.bo.DecisionProductBo;
import com.btjf.credit.report.decision.dock.enums.DecisionDockEnum;
import com.btjf.credit.report.decision.dock.exception.DecisionDockException;
import com.btjf.credit.report.decision.dock.pojo.DecisionProduct;
import com.btjf.credit.report.decision.dock.service.DecisionDockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/13
 * @time 下午6:12
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */

@Service
public class DecisionDockNativeDomain implements DecisionDockDomain{
    private static final Logger LOGGER = LoggerFactory.getLogger(DecisionDockNativeDomain.class);
    @Resource
    private DecisionDockService decisionDockService;

    @Override
    public List<DecisionProductBo> getProductList() {
        Date startDate = new Date();
        List<DecisionProduct> decisionProducts = decisionDockService.getProductList();

        List<DecisionProductBo> decisionProductBos = BeanUtil.convertList(decisionProducts, DecisionProductBo.class);
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.PRODUCT_LIST+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ JSON.toJSONString(decisionProductBos));
        return decisionProductBos;
    }

    @Override
    public String realNameCheck(String name, String idCard) {
        Date startDate = new Date();
        String result;
        try {
            result = decisionDockService.realNameCheck(name,idCard);
        } catch (DecisionDockException e) {
            throw new DecisionDockException(DecisionDockEnum.ResultCode.ERROR_NAME_IDCARD.getValue());
        }
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.REAL_NAME_CHECK+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ result);
        return result;
    }

    @Override
    public String mobileRealCheck(String name, String idCard, String mobile) {
        Date startDate = new Date();
        String result;
        try {
            result = decisionDockService.mobileRealCheck(name,idCard,mobile);
        } catch (DecisionDockException e) {
            throw new DecisionDockException(DecisionDockEnum.ResultCode.ERROR_MOBILE.getValue());
        }
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.MOBILE_REAL_CHECK+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ result);
        return result;
    }

    @Override
    public String bankCardRealCheck(String name, String idCard, String bankCard) {
        Date startDate = new Date();
        String result;
        try {
            result = decisionDockService.bankCardRealCheck(name,idCard,bankCard);
        } catch (DecisionDockException e) {
            throw new DecisionDockException(DecisionDockEnum.ResultCode.ERROR_BANKCARD.getValue());
        }
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.BANKCARD_REAL_CHECK+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ result);
        return result;
    }
    @Override
    public String checkBankCard(String bankCard) {
        Date startDate = new Date();
        String result;
        try {
            result = decisionDockService.checkBankCard(bankCard);
            if(result == null){
                throw new DecisionDockException(DecisionDockEnum.ResultCode.VALID_BANKCARD_ERROR.getValue());
            }
        } catch (DecisionDockException e) {
            throw new DecisionDockException(DecisionDockEnum.ResultCode.VALID_BANKCARD_ERROR.getValue());
        }
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.CHECK_BANKCARD+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ result);
        return result;
    }


    @Override
    public String bigDataReportNum(String name, String idCard, String bankCard, String mobile) {
        Date startDate = new Date();
        String result = decisionDockService.bigDataReportNum(name,idCard,bankCard,mobile);
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.BIGDATA_REPORT_NUM+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ result);
        return result;
    }

    @Override
    public String aliPayReportNum(String name, String idCard, String mobile) {
        Date startDate = new Date();
        String result =  decisionDockService.aliPayReportNum(name,idCard,mobile);
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.ALIPAY_REPORT_NUM+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ result);
        return result;
    }

    @Override
    public String badReportNum(String name, String idCard, String mobile) {
        Date startDate = new Date();
        String result = decisionDockService.badReportNum(name,idCard,mobile);
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.BAD_REPORT_NUM+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ result);
        return result;
    }

    @Override
    public String creditRecordReportNum(String name, String idCard, String mobile) {
        Date startDate = new Date();
        String result = decisionDockService.creditRecordReportNum(name,idCard,mobile);
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.CREDITRECORD_REPORT_NUM+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ result);
        return result;
    }

    @Override
    public String bigDataReportContent(String name, String idCard, String mobile, String reportNo) {
        Date startDate = new Date();
        String result = decisionDockService.bigDataReportContent(name,idCard,mobile,reportNo);
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.BIGDATA_REPORT_CONTENT+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ result);
        return result;
    }

    @Override
    public String aliPayReportContent(String name, String idCard, String mobile, String reportNo) {
        Date startDate = new Date();
        String result =  decisionDockService.aliPayReportContent(name,idCard,mobile,reportNo);
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.ALIPAY_REPORT_CONTENT+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ result);
        return result;
    }

    @Override
    public String badReportContent(String name, String idCard, String mobile, String reportNo) {
        Date startDate = new Date();
        String result = decisionDockService.badReportContent(name,idCard,mobile,reportNo);
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.BAD_REPORT_CONTENT+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ result);
        return result;
    }

    @Override
    public String creditRecordReportContent(String name, String idCard, String mobile, String reportNo) {
        Date startDate = new Date();
        String result = decisionDockService.creditRecordReportContent(name,idCard,mobile,reportNo);
        int useTime = (int) (new Date().getTime() - startDate.getTime());
        LOGGER.info("=="+ DecisionDockEnum.InterfaceType.CREDITRECORD_REPORT_CONTENT+"=="+this.getClass().getName()+"方法共耗时："+useTime+"==返回 "+ result);
        return result;
    }
}
