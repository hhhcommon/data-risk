package com.btjf.credit.report.decision.dock.service;

import com.alibaba.fastjson.JSON;
import com.btjf.common.http.HttpHandle;
import com.btjf.common.http.HttpHandleFactory;
import com.btjf.common.http.HttpResult;
import com.btjf.common.utils.DateUtil;
import com.btjf.credit.report.decision.dock.enums.DecisionDockEnum;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zsw on 2017/6/12.
 *
 * @Description:
 */
@Service
public class CommonService {

    private static Logger log = LoggerFactory.getLogger(DecisionDockService.class);

    @Value("${decision.appId}")
    private String appId;

    @Value("${decision.appkey}")
    private String appKey;

    @Value("${decision.getReportNum.url}")
    private String getReportNumUrl;

    @Value("${decision.getReportContent.url}")
    private String getReportContentUrl;

    private static final String reqHeaderKey = "wallet-auth-signature";

    public String send2decision(String url, String productId, Map<String ,String> parmMap){

        //去空格
        productId = productId.replaceAll("\\s*", "");
        url = url.replaceAll("\\s*", "");

        String parmJsonStr = parmMap == null ? null : this.getParmJsonStr(parmMap);
        //去空格
        if(!StringUtils.isBlank(parmJsonStr)){
            parmJsonStr = parmJsonStr.replaceAll("\\s*", "");
        }

        Map<String, String> header = this.generatHeader(productId, parmJsonStr);
        Map<String, String> reqParmMap = Maps.newHashMap();
        String desParm = this.generatDES(parmJsonStr);
        reqParmMap.put("data", desParm);

        HttpHandle httpHandle = HttpHandleFactory.getDefaultHandle();
        String resultStr = null;
        if(httpHandle != null){
            HttpResult result =  httpHandle.sendSSLPostRequest(url,header, reqParmMap,"UTF-8");
            resultStr = result.getBody();

        }
        return resultStr;

    }

    private Map<String, String> generatHeader(String productId, String parmJson){
        String timestamp = DateUtil.dateToString(new Date(), DateUtil.ymdhmsFormat);
        String sign = this.createSign(productId, parmJson, timestamp);

        StringBuilder toHeaderSb = new StringBuilder();
        toHeaderSb.append("appId=").append(appId).append("&");
        toHeaderSb.append("productId=").append(productId).append("&");
        toHeaderSb.append("timestamp=").append(timestamp).append("&");
        toHeaderSb.append("sign=").append(sign);

        Map<String,String> reqHeaderMap = new HashMap<>();
        reqHeaderMap.put(reqHeaderKey,toHeaderSb.toString());

        return reqHeaderMap;
    }

    private String createSign(String productId, String parmJson, String timestamp) {

        StringBuilder toSignSb = new StringBuilder();
        toSignSb.append("appId=").append(appId).append("&");
        toSignSb.append("appKey=").append(appKey).append("&");
        toSignSb.append("productId=").append(productId).append("&");

        if (StringUtils.isNotBlank(parmJson)) {
            toSignSb.append("req=").append(parmJson).append("&");
        }

        toSignSb.append("timestamp=").append(timestamp);

        String sign = null;
        try {
            sign = Signature.md5Digest(toSignSb.toString()).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sign;

    }

    private String generatDES(String parmJson) {
        if (StringUtils.isBlank(parmJson)) {
            return null;
        }
        try {
            return SecurityUtils.encode(parmJson.getBytes(), appKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decryptDes(String desStr) {
        try {
            return SecurityUtils.decode(desStr, appKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getParmJsonStr(Map<String, String> parmMap) {
        return JSON.toJSONString(parmMap);
    }

}
