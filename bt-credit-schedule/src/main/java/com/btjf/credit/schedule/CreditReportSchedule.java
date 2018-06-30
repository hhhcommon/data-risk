package com.btjf.credit.schedule;

import com.btjf.common.utils.DateUtil;
import com.btjf.credit.report.bo.CreditReportBo;
import com.btjf.credit.report.constant.ModuleConstant;
import com.btjf.credit.report.constant.NodeConstant;
import com.btjf.credit.report.domain.CreditReportDomain;
import com.btjf.credit.report.enums.CreditReportEnum;
import com.btjf.credit.report.exception.CreditReportExcetion;
import com.icar.finance.event.EventBasiceMessageProducer;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/7/6
 * @time 下午1:26
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */

@Component
public class CreditReportSchedule {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditReportSchedule.class);
    @Resource
    private CreditReportDomain creditReportDomain;

    /**
     * 定时获取查询中的征信报告
     * 主动向决策系统获取征信报告结果
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 60 * 10)
    public void inQueryDataSync(){
        LOGGER.info("======征信报告定时任务开始======");
        creditReportDomain.inQueryDataSync();
        LOGGER.info("======征信报告定时任务结束======");
    }


}
