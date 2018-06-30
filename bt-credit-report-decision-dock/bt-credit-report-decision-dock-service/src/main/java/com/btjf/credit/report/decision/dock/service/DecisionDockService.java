package com.btjf.credit.report.decision.dock.service;

import com.alibaba.fastjson.JSONObject;
import com.btjf.credit.report.decision.dock.enums.DecisionDockEnum;
import com.btjf.credit.report.decision.dock.exception.DecisionDockException;
import com.btjf.credit.report.decision.dock.mapper.CreditReportDecisonDockLogMapper;
import com.btjf.credit.report.decision.dock.model.CreditReportDecisonDockLog;
import com.btjf.credit.report.decision.dock.pojo.DecisionProduct;
import com.btjf.credit.report.decision.dock.pojo.DecisionResult;
import com.btjf.credit.report.decision.dock.pojo.RealCheck;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/13
 * @time 上午10:19
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */
@Service
public class DecisionDockService {

    @Value("${decision.productList.url}")
    private String productListUrl;

    @Value("${decision.getReportNum.url}")
    private String reportNumUrl;

    @Value("${decision.getReportContent.url}")
    private String reportContentUrl;

    @Value("${dicision.getAsynReportContent.url}")
    private String asynReportContent;

    @Resource
    private CommonService commonService;

    @Resource
    private CreditReportDecisonDockLogMapper creditReportDecisonDockLogMapper;

    /**
     * 获取所有可用决策产品
     * @return  List<DecisionProduct>
     */
    public List<DecisionProduct> getProductList(){
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        String url = this.productListUrl;
        String productId = DecisionDockEnum.Product.LIST.getValue();
        Map<String,String > map = new HashMap<>();
        String productsJson = commonService.send2decision(url, productId, map);

        //写日志
        creditReportDecisonDockLog.setUrl(this.productListUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(map));
        creditReportDecisonDockLog.setReportDesc(getReportDes(this.productListUrl));

        String decryptDes = returnData(productsJson,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        List<DecisionProduct> decisionProducts = JSONObject.parseArray(decryptDes, DecisionProduct.class);

        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);

        return decisionProducts;
    }


    /**
     * 姓名身份证二元素验证
     * @param name  姓名
     * @param idCard    身份证
     * @return  Boolean
     */
    public String realNameCheck(String name, String idCard){
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        Map<String, String> parmMap = Maps.newHashMap();
        parmMap.put("userName",name);
        parmMap.put("userIdCard",idCard);
        String productId = DecisionDockEnum.Product.NAME_IDCARD.getValue();
        String productsJson = commonService.send2decision(this.reportNumUrl, productId, parmMap);

        //写日志
        creditReportDecisonDockLog.setUrl(this.reportNumUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(parmMap));
        creditReportDecisonDockLog.setReportDesc(getReportDes(this.reportNumUrl));

        //productsJson = {"resultCode":"1004","resultDesc":"参数格式不对","data":null}
        String decryptDes = returnData(productsJson,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }

        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);
        return getCheckResult(decryptDes);
    }

    /**
     * 姓名身份证手机三要素验证
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @return  Boolean
     */
    public String mobileRealCheck(String name, String idCard, String mobile){
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        Map<String, String> parmMap = Maps.newHashMap();
        parmMap.put("userName",name);
        parmMap.put("userIdCard",idCard);
        parmMap.put("userPhone",mobile);
        String productId = DecisionDockEnum.Product.NAME_IDCARD_MOBILE.getValue();
        String productsJson = commonService.send2decision(this.reportNumUrl, productId, parmMap);

        //写日志
        creditReportDecisonDockLog.setUrl(this.reportNumUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(parmMap));
        creditReportDecisonDockLog.setReportDesc(getReportDes(this.reportNumUrl));

        String decryptDes = returnData(productsJson,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);
        return getCheckResult(decryptDes);
    }

    /**
     * 姓名身份证银行卡三要素验证
     * @param name  姓名
     * @param idCard    身份证
     * @param bankCard  银行卡
     * @return     Boolean
     */
    public String bankCardRealCheck(String name, String idCard, String bankCard){
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        Map<String, String> parmMap = Maps.newHashMap();
        parmMap.put("userName",name);
        parmMap.put("userIdCard",idCard);
        parmMap.put("bankCardNo",bankCard);
        String productId = DecisionDockEnum.Product.NAME_IDCARD_BANDCARD.getValue();
        String productsJson = commonService.send2decision(this.reportNumUrl,productId , parmMap);

        //写日志
        creditReportDecisonDockLog.setUrl(this.reportNumUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(parmMap));
        creditReportDecisonDockLog.setReportDesc(getReportDes(this.reportNumUrl));

        String decryptDes = returnData(productsJson,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);
        return getCheckResult(decryptDes);
    }

    /**
     * 银行卡验证
     * @param bankCard  银行卡
     * @return     String   0:无效卡号；1：借记卡:2：信用卡
     */
    public String checkBankCard(String bankCard){
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        Map<String, String> parmMap = Maps.newHashMap();
        parmMap.put("bankCardNo",bankCard);
        String productId = DecisionDockEnum.Product.CHECK_BANKCARD.getValue();
        String productsJson = commonService.send2decision(this.reportNumUrl,productId , parmMap);

        //写日志
        creditReportDecisonDockLog.setUrl(this.reportNumUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(parmMap));
        creditReportDecisonDockLog.setReportDesc(getReportDes(this.reportNumUrl));

        String decryptDes = returnData(productsJson,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);

        return getCheckResult(decryptDes);
    }

    private String getCheckResult(String productsJson){
        RealCheck realCheck = JSONObject.parseObject(productsJson, RealCheck.class);
        if (realCheck == null)return null;
        RealCheck.Auth auth = realCheck.getAuth();
        if(auth == null)return null;
        return auth.getResult();
    }

    /**
     * 获取大数据报告编号
     * @param name  姓名
     * @param idCard    身份证
     * @param bankCard  银行卡
     * @param mobile    手机号
     * @return  String
     */
    public String bigDataReportNum(String name, String idCard, String bankCard,String mobile){
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        Map<String, String> parmMap = Maps.newHashMap();
        parmMap.put("userName",name);
        parmMap.put("userIdCard",idCard);
        parmMap.put("bankCardNo",bankCard);
        parmMap.put("userPhone",mobile);
        parmMap.put("postUrl",asynReportContent);
        String productId = DecisionDockEnum.Product.FULL_REPORT.getValue();
        String productsJson = commonService.send2decision(this.reportNumUrl,productId , parmMap);

        //写日志
        creditReportDecisonDockLog.setUrl(this.reportNumUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(parmMap));
        creditReportDecisonDockLog.setReportDesc(getReportDes(this.reportNumUrl));

        String decryptDes = returnData(productsJson,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);
        return decryptDes;
    }

    /**
     * 支付宝报告编号
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @return  String
     */
    public String aliPayReportNum(String name, String idCard,String mobile){
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        Map<String, String> parmMap = Maps.newHashMap();
        parmMap.put("userName",name);
        parmMap.put("userIdCard",idCard);
        parmMap.put("userPhone",mobile);
        parmMap.put("postUrl",asynReportContent);
        String productId = DecisionDockEnum.Product.ALIPAY_REPORT.getValue();
        String decision = commonService.send2decision(this.reportNumUrl,productId , parmMap);

        //写日志
        creditReportDecisonDockLog.setUrl(this.reportNumUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(parmMap));
        creditReportDecisonDockLog.setReportDesc(getReportDes(this.reportNumUrl));

        String decryptDes = returnData(decision,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);
        return decryptDes;
    }

    /**
     * 不良记录报告编号
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @return  String
     */
    public String badReportNum(String name, String idCard,String mobile){
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        Map<String, String> parmMap = Maps.newHashMap();
        parmMap.put("userName",name);
        parmMap.put("userIdCard",idCard);
        parmMap.put("userPhone",mobile);
        parmMap.put("postUrl",asynReportContent);
        String productId = DecisionDockEnum.Product.BAD_REPORT.getValue();
        String decision = commonService.send2decision(this.reportNumUrl,productId , parmMap);

        //写日志
        creditReportDecisonDockLog.setUrl(this.reportNumUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(parmMap));
        creditReportDecisonDockLog.setReportDesc(getReportDes(this.reportNumUrl));

        String decryptDes = returnData(decision,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);
        return decryptDes;
    }

    /**
     * 信贷记录报告编号
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @return  String
     */
    public String creditRecordReportNum(String name, String idCard,String mobile){
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        Map<String, String> parmMap = Maps.newHashMap();
        parmMap.put("userName",name);
        parmMap.put("userIdCard",idCard);
        parmMap.put("userPhone",mobile);
        parmMap.put("postUrl",asynReportContent);
        String productId = DecisionDockEnum.Product.CREDIT_REPORT.getValue();
        String decision = commonService.send2decision(this.reportNumUrl,productId , parmMap);

        //写日志
        creditReportDecisonDockLog.setUrl(this.reportNumUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(parmMap));
        creditReportDecisonDockLog.setReportDesc(getReportDes(this.reportNumUrl));

        String decryptDes = returnData(decision,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);
        return decryptDes;
    }

    /**
     * 获取大数据报告内容
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @param reportNo   报告编号
     * @return  String
     */
    public String bigDataReportContent(String name, String idCard ,String mobile, String reportNo){
        //失败样例 {"resultCode":"1002","resultDesc":"商户不存在","data":null}
        //成功样例 {"resultCode":"0000","resultDesc":"成功","data":"B22E2047BBA8E199F854AAE0DE3535A970A7A9EEF3B02B390CAA03689EFD35D590FE77974388D4ACEFAC71842756CBE6011509035DE1BE8FEB901B2EB85E8389071DA6FBB11F5652159CEAD655106FA2F34861C9D277461908A48C98CB815751F6B5F7534EA094C7B8CE022EA63AE909F9B75B494B3A86C56943B122723A786932161E5808E76DDA6C9C9BB434F6426BFBDB715BCE66DF13C8EDAC8DE1C85E6583EDA69CBE86B2CCF712C7EAD17CE06D378B201A888C403E32FFFD388C0B9E7E0F051BEB1B66708B340082AA444B351303578C089A1DB453C4292C162405E672DCC8AE59BC3ED4B65F108CBB8A11721BC6AD45EEC5C7968068D4B50429D2D390F84F8CFB5CAC9EB275A80D17C7C50C50817454DAEA01AE6DAA0C51570AD4A0794D8125AF48CACBE1665AB6C6232DF63052972042E6CE3324D473D6CFA2860E99CF5C4D71085796A925EBFBDA7965DC9F9C187031A16590CE1F75BEEDED596192FF14968CBBE626B737D05A9EEB7FC925DA1EE9813B797A58EFDC8A1C7D16E5CF0F7466BB1CC1CB8E37140214559684F60DF44239088F9773FE7338F546EC940480C605B6503D39ED1B1E64383008BD733F7C3CD36986E93427ADAA1495A913DE2ABD3EB5D1B9EA40A587277B5214AEE53465E49A3592C92D111B16DCC5D7C9C5C12EF1324FF69A6C5956745D2E91BB1FC0645A4114045F47D6264148500E525B79E3764725C05F07F112C59F4B6B86DDEB9696FA499952A2624B75ACB46A02E976267AD8374E8CBD81ED3FFC4E3C5187782C7E76B5E7A34D553BD3C29907ADFB8B313E3A497FE09C9C7EAF2B0938E1A34414A9B1E7DF12E197B6165DBA985DF5BC758B7C82BB654E268D864B57C865CA4E10C2B2B37523DF902B7CBA187CAC76794B573FE9C71DAD06533BB80586212C62867C6A85C095B7C73E52BAB420666F5A3E86B38D7BF43BF9C164FDFB3C2710DBEC461CEC2313B808D4EC876C20237497BE385C8C987ADBA571949D159BE6B329C3FCAA99BF91D55F455ADD69309EF1E2837CC583AF6CD3CC77FD1F1C110D2584D66F3A4F54E7C78A72854841CE407F41BDE7F2D0EBE1479F17E3D2B5D7AEA275AAB6E00A5117930086E4D78C4C040E4277096F599F0643393CB428C1EA8AD8C0369B1788B07E1D959598B34948F9728577D4B2042E2F18430D162F2C78EBC3E99AD0A4CCDEE50535635BCCF9D86BD1B0CA929578054C6F07EA8D31757491F77FA2C92F3C8E8E66DCDF7BBC77D3F6DD40111F5AB0C4BCA02B30589AC13AD0F08C2B161F8B6D3D9EC45E9D70EE6433B1495DA781B46AF2D7F5704E6F24218AC56D6A6A12A3F8D15A7E1676BF4CA40BF16D6AF9E3FCC84719C40346FC30B1189F7DE8B893B5B0526B3E753C9ED0EF5B1B7FABA68E4FBCC3548ED1C97DED54813DB03BD0EE9B41C0F679833F42DFB686EE2A5208CC447538E263B1F20CAA96A90282E8058E6811641577A7E0293943A0915AEE328DF9636830BBC4F4BDDD4ED512463F48FA35293735CD8E993FA6195E43D9650EC8DA2C08CB843FA042287EB859EE4FD57D176E0B7046DBBA2E0B4B36FA3D4386FA474201A1DEF199D86990F0D987C12A494F7FFF3E2EC7577CB7664A9D1FCAC6A3DCA8173EE008E9A3C52A05B316365EC294E8EA02519EADC16D508B3E430BE7675B38EC766C1789D40A7D9200406878ABFF6466029F8D160AA7C7A2F02160EC800C9364FB1ADBD6ADAD90E274D850DB79CA204AEA0899F72F41046A2ED3D110947AF547B03A1E8DB1D66FCC161CDFF7285B8CE9DC471CF7DD485E5553F5E466DFCCB3991E7C4671824D91046FCF20DA215AD5A2470093EE47D0C67EA43D68E3BD138521F987357647A1D6D40609AF4ED7DADD2387C91FB7EBD2F7ACF5219E374458195C292F3B1309442960BE310BB4E5B9E5BAB39AF1D69D4BC43AC842F6FFFBB7922284227A556B0345FAB8F09A81A65E35A023D4DF65F23CEE5004D79E2A9F2DC98D1068D75411C8F2C6B61936956C2D090EAF1D626B75CAE06FEAEF0EC82B10337EC3E37BDFE0093FF1A08D40F6C61619A73CFFC60A1F63ACD6956FB8EF425A130351E8BDDF5773C593757BF4488F74AB6A77A9693F7FF0C744236483D1DADED8260CCE17922C7FD56CC2F0EAA16FFE4CEEBF641520DF109E39B6C524C65D0B298F0B85306EC401055CE36DD4450FF4B3B901765A6DEE27AF1A52192CBFB585F1051A7F57D5480F231CCE47DFC50F4D0F4989F2094E3F75411C248E132C377C7AA6FCFEBD49AD49A4C86E35193DD2BB306830BCC142A961CDCECE12968D1F52025A9EE2929D24FB39B3F0FD75FDFE27C5B188B3C361D3BFFD68DAA5AB409C358708455EAD4A8AD8285E387A90B20856D62DB9EF5DC0493BA6A00848707EA4E17C03D4DA93D57B5835A00D571054AB5B3CD9309D7805928085D58D49CA401D8C698814F64F2FD492D24D8B23EFAA10A85C5EBED309F0A547A080A108C939149E8BD30107676DA509503571C62CF76161477B8ADA449286CD0142E57FB145E1CA2F5B79E25EC36F8C362D83757637023073DC6E8BD82AED674BDB3388C8F4652BBA535E0DF767CC28B1D7B857AA7FB7A8BABAF9AB3E8F3104ECE721D6B60F912FB514A9B0F67FC13A29E04B0727396CDC5016E17045C55CE9886FEE2789FA93953923F470B8462BD5340B9B7C81391364F0264458188060537EE962D3F830EF2263A08A5C375B6A1B20DF7D3BD4C9F9B43D5A8B6D53B4FE0876F085B96D0B18F61EA09BDF1DEADE5B06C7D1149CB7443B406B13C8FACC43DFC48DD3D1CDC0F26F78EB6D0F0A56AB05FD52F51E50C8B25A551D3FFD45DB3758454D77B65CA67D9FCE906722FC8E2633D7C015D2F9A42D65E208F2023CD7D410C610857328967D1BB894E9A53E8D669D4FEEEAD1A5DDE5E2A39E77F431D0F487A8C829EB462E3059D93C7415AF877B5FCC3F27C64A2D3DF1B6087E854F1AEB6F5E9B3A84636823E473ACB7CC36926FC5392F13FE117406F26158A9FBA3433E5F31557CEAF6DC06A5C10D18E8A87720292FAF8A732A0BAFF5693F85D30412C7F75447600E695082EF07DE3C2FDBE588374FCBFEAA6196670A8C7DCB9112FA02134D503212B3F067C5A3236F6CB12E73497E8FB559725395041DF9A77CF51ADF0EBE8C15A89DCDECBFE65F8A13011B75DAA4DCC4A0035492FE5BCB299DF015A7392894C6C9B1271E3666843E2AE6ADCBF73FA56D4B2E7545FE5B48FEE527F14B59C18372FBBB6AC848556D71AB9267AD097F416BEDB18223EBFE30B844143471D4518FD8E9D97DBA5B27925DDD69E33DEA86D10A404236D436433BE81E764D9EDE37545A0A220279A12714E1E44C13E861CF4D3A56A4052C33D72D50648BCB98E982DCB0E6E286102BAA7353F63649802D9CDD36344C381861CA8E419166772F9E8B5AEED0E836675A7BA14D2B7B5DCCB26671A4798146BF3CF4F863614B0BF8167D22BBE3D26816D7360B734873C4A580CDA82E976BBD4D144592B1B376E586011468E55703D147215543E465780348EF5E428B2103A638AECC732C6453DA0EB5D4EB4DA3E97CAD2CE95DCB8214AB88D8A5696E6689BFF10BA14A39CA76633B24BFDECFF159D33871017B2E301EACA39245C399C3913502BAC870D6E2DCA9FFB906051B0AF95A5FAA2EE91BBB34B5BCACD72403AD04B6519AFAAE983758A57ED4849CE256056DFEF535E8F15FF9E4E9F51D47FA40844546953B22B637663B8B9F8FCABDE16890C624B65902C3287664904C86E9F4CC7B8E80F677E9FA4D68DE8B87A12CC15CD91F579BA1293CAA6AD9E53FDF5E39F647E0F5BF367623DA1D0ECF7F7034F4BE7C654387EAD0221E5D6615A4DCD9A4B66CBFF90E79A692672F9F6DD8A106A806F5F1CF42A3578BF581A1DDB8FD4E0A85A5C7D1A23EB94FBB513ED61E22141358D77A0138EBC36CF5A2267C8B9EEFE7ACF41F357D67C600151B55DAE65F895CFA3FF2CA04C0BFC36DA38411FCBE10D1EFE4018C848A290B7E1CFF7D45509DA0238892E5DAA2048BCBE4DCAFF619346D90EBCAC7991C399FE2D63CACC54E0E381E251261B8F0361B68819A1AB615A0C9EBF93482EC53519158F9F0B4EB4DB6C8612A7F68E751B2AF3DB48E9D0AA2822F2A7ECE54F4226792AB3426D65D473ED1D70E84A8EE0DA811DF937B078B2F286557568B922393504E5CF4A252429CA685B230EB7A844425965FBC367E284B1F3A441C2196997C085B4CF6DC764E6A4CB3242000370C3B16A2ECDD91A8BAA8DCA85CA20097435C7B6CB2514BF344085A93311BADC7E16DCE28F7D8E826FF2F0F21E109DB31E5FC4DDB67C5F086220D0906CD2A82D1E439DA036FCD8F050372CB18ADA4C43ED6BF4405BB7E0D90D6D261477FDF8E3064F188919F3AFAA718036DA5E4AFCDE0B0CB428A81CF267618C708FFF16259249D5117C86728C88D90E6C81EF1B03F1D4F0002CDD8922CEDA20D8ACA0BF57661F3338ADA4BEC167BB3C870A79303ECF2134528ED41CFF40366E674FE79C4C1A9D8559952B5055ECF379071313AAF6E9808240C9F3E2D5E646F239EEA2168B6D05063B3294AE928431A5C217A4999EC036D2CE9515EA96AE200B78E096977B078ACC1AD2F0A8780BD42939A4D5A3B9513814332569F78B8CFCDA90394C11328CD8E171C6D1D274681E5890EFA1CB96B275E221C8BFA64CA1ACB50071F4AA2F9C3B671ACB297B145FE5A65A4B5C4D8FE962EE5FB80233546BA96D50EB4B51651F6AF0FE1E4830BC47BCE2DE96DB1789BADB42F7917981169D48C2B335CABE4FD298D0B5ABB8368C6758DE37A5C08AE5720C598FD8F425FBD52F3641858CD8F00318DAB06104A66672D24388BA02FA7B714334F66DB065957F28CF976C6E1AC77831FD847C8B3F49E401E6B0A874EBAD8655E5DAD68C05078F0FCBCB076DEC64B22E3CCD5780B9F4A320B744DC26C1B259E5F37A909E84B8142F0B13720715D213CE6DF4D23DE836824B7B8106EC0EB1D52FB20AF6121D1C684FCF1AA7C94815A2580FDB08D9B39BA9033C9DC645FCFEF9A4EF83CFDA84B5AA243A30DB3DC78934B501DBE22CC4906198A4314705BB1BA241C7C2CC734FD059A5CA89BD33761893BB6505C2C1F847C8481C49C5CECE3339907A46935415A423D763B693F32EDEBE45E5786F53C7E64833E6E312490490BA6025DDE48489510E967E54C44C0C582E15F7B277C7220DA23FB782E565861FB1854A1E15EE938252848F3EB83A8BE8CDD17B3F5FE25ECA9266AE615A5B99713E72C8885F9F54ACCD1B75F639683EF9F7C08AFFF0C59F3C4899B65FCD32775AF8BC9C63C1084E44D280773AE0ED984637112EB12ADEBD60DCF90FD66CD9750FABE21C914E1A708BC9E23A3990C4511CE09A560D35B7812F5D49A5790F19BBB969C2C7BEEC389CC5E0DC1461A7533D226102E11A7625FA96107993A204B6F343077104744"}
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        Map<String, String> parmMap = Maps.newHashMap();
        parmMap.put("userName",name);
        parmMap.put("userIdCard",idCard);
        parmMap.put("userPhone",mobile);
        parmMap.put("reportNo",reportNo );
        parmMap.put("postUrl",asynReportContent);
        String productId = DecisionDockEnum.Product.FULL_REPORT.getValue();
        String productsJson = commonService.send2decision(this.reportContentUrl,productId , parmMap);

        //写日志
        creditReportDecisonDockLog.setUrl(this.reportContentUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(parmMap));
        creditReportDecisonDockLog.setReportDesc(getReportDes(this.reportContentUrl));

        String decryptDes = checkDecisionResult(productsJson,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);
        return decryptDes;
    }

    /**
     * 支付宝报告内容
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @return  String
     */
    public String aliPayReportContent(String name, String idCard,String mobile, String reportNo){
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        Map<String, String> parmMap = Maps.newHashMap();
        parmMap.put("userName",name);
        parmMap.put("userIdCard",idCard);
        parmMap.put("userPhone",mobile);
        parmMap.put("reportNo",reportNo );
        parmMap.put("postUrl",asynReportContent);
        String productId = DecisionDockEnum.Product.ALIPAY_REPORT.getValue();
        String decision = commonService.send2decision(this.reportContentUrl,productId , parmMap);

        //写日志
        creditReportDecisonDockLog.setUrl(this.reportContentUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(parmMap));
        creditReportDecisonDockLog.setReportDesc(getReportDes(this.reportContentUrl));

        String decryptDes = checkDecisionResult(decision,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);
        return decryptDes;
    }

    /**
     * 不良记录报告内容
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @return  String
     */
    public String badReportContent(String name, String idCard,String mobile, String reportNo){
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        Map<String, String> parmMap = Maps.newHashMap();
        parmMap.put("userName",name);
        parmMap.put("userIdCard",idCard);
        parmMap.put("userPhone",mobile);
        parmMap.put("reportNo",reportNo );
        parmMap.put("postUrl",asynReportContent);
        String productId = DecisionDockEnum.Product.BAD_REPORT.getValue();
        String decision = commonService.send2decision(this.reportContentUrl,productId , parmMap);

        //写日志
        creditReportDecisonDockLog.setUrl(this.reportContentUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(parmMap));

        creditReportDecisonDockLog.setReportDesc(getReportDes(this.reportContentUrl));

        String decryptDes = checkDecisionResult(decision,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);
        return decryptDes;
    }

    /**
     * 信贷记录报告内容
     * @param name  姓名
     * @param idCard    身份证
     * @param mobile    手机号
     * @return  String
     */
    public String creditRecordReportContent(String name, String idCard,String mobile, String reportNo){
        CreditReportDecisonDockLog creditReportDecisonDockLog = new CreditReportDecisonDockLog();
        creditReportDecisonDockLog.setStartTime(new Date());

        Map<String, String> parmMap = Maps.newHashMap();
        parmMap.put("userName",name);
        parmMap.put("userIdCard",idCard);
        parmMap.put("userPhone",mobile);
        parmMap.put("reportNo",reportNo );
        parmMap.put("postUrl",asynReportContent);
        String productId = DecisionDockEnum.Product.CREDIT_REPORT.getValue();
        String decision = commonService.send2decision(this.reportContentUrl, productId, parmMap);

        //写日志
        creditReportDecisonDockLog.setUrl(this.reportContentUrl);
        creditReportDecisonDockLog.setProductId(Integer.valueOf(productId));
        creditReportDecisonDockLog.setParmMap(commonService.getParmJsonStr(parmMap));
        creditReportDecisonDockLog.setReportDesc(getReportDes(this.reportContentUrl));

        String decryptDes = checkDecisionResult(decision,creditReportDecisonDockLog);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        creditReportDecisonDockLog.setEndTime(new Date());
        creditReportDecisonDockLogMapper.insertSelective(creditReportDecisonDockLog);
        return decryptDes;
    }

    /**
     * 解刨决策系统返回结果
     * 流程：
     * 1、先判断是否有data，有则取出来
     * 2、DES解密data数据
     * @param productsJson  决策系统返回结果
     * @return  String
     */
    private String returnData(String productsJson,CreditReportDecisonDockLog creditReportDecisonDockLog){
        String dataStr = checkDecisionResult(productsJson,creditReportDecisonDockLog);
        if(StringUtils.isBlank(dataStr)){
            return null;
        }
        String decryptDes = commonService.decryptDes(dataStr);
        if(StringUtils.isBlank(decryptDes)){
            return null;
        }
        return decryptDes;
    }

    /**
     * 获取决策系统json包，返回最终data数据
     * @param jsonStr   json包
     * @return  String
     */
    private String checkDecisionResult(String jsonStr,CreditReportDecisonDockLog creditReportDecisonDockLog){
        if (StringUtils.isBlank(jsonStr)){
            return null;
        }
        DecisionResult decisionResult = JSONObject.parseObject(jsonStr, DecisionResult.class);
        if(decisionResult == null){
            return null;
        }
        creditReportDecisonDockLog.setResultCode(decisionResult.getResultCode());
        creditReportDecisonDockLog.setResultDesc(decisionResult.getResultDesc());
        creditReportDecisonDockLog.setData(decisionResult.getData());

        if(!DecisionDockEnum.ResultCode.SUCCESS.getValue().equals(decisionResult.getResultCode())){
            DecisionDockEnum.ResultCode resultCode = DecisionDockEnum.ResultCode.getByValue(decisionResult.getResultCode());
            if(resultCode == null){
                throw new DecisionDockException(DecisionDockEnum.ResultCode.PARAMETER_ERROR.getContent());
            }
            throw new DecisionDockException(resultCode.getContent());
        }
        return decisionResult.getData();
    }

    /**
     * 判断请求决策系统获取结果的类型
     * @param url   通过请求URL判断
     * @return  String
     */
    private String getReportDes(String url){
        if(StringUtils.isBlank(url)){
            return null;
        }
        if(this.productListUrl.equals(url)){
            return DecisionDockEnum.InterfaceType.GET_REPORT_PRODUCT_LIST.getContent();
        }else if(this.reportNumUrl.equals(url)){
            return DecisionDockEnum.InterfaceType.GET_REPORT_NUM.getContent();
        }else if(this.reportContentUrl.equals(url)){
            return DecisionDockEnum.InterfaceType.GET_REPORT_CONTENT.getContent();
        }
        return null;
    }
}
