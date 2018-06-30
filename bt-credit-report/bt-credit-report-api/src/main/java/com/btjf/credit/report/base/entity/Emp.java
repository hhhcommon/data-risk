package com.btjf.credit.report.base.entity;

import com.btjf.business.organize.emp.bo.EmpBo;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author L.C
 * @version 0.0.1
 * @date 2017/6/19
 * @time 上午11:19
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 */
public class Emp {
    @ApiModelProperty(value = "员工信息ID")
    private Integer ffEmpID;
    @ApiModelProperty(value = "员工用户名")
    private String ffUserName;
    @ApiModelProperty(value = "员工姓名")
    private String ffName;
    @ApiModelProperty(value = "性别")
    private Integer ffSex;
    @ApiModelProperty(value = "生日")
    private Date ffBirthday;
    @ApiModelProperty(value = "入职日期")
    private Date ffInDate;
    @ApiModelProperty(value = "学历")
    private Integer ffDegrees;
    @ApiModelProperty(value = "身份证号")
    private String ffIDCard;
    @ApiModelProperty(value = "地址")
    private String ffAddress;
    @ApiModelProperty(value = "身份证地址")
    private String ffIDCardAddress;
    @ApiModelProperty(value = "是否结婚")
    private Integer ffMarital;
    @ApiModelProperty(value = "职位")
    private Integer ffPosition;
    @ApiModelProperty(value = "手机")
    private String ffMobile;
    @ApiModelProperty(value = "备用手机")
    private String ffSpareMobile;
    @ApiModelProperty(value = "部门ID")
    private Integer ffDeptID;
    @ApiModelProperty(value = "备注")
    private String ffRemark;
    @ApiModelProperty(value = "密码")
    private String ffPwd;
    @ApiModelProperty(value = "支付密码")
    private String ffPayPwd;
    @ApiModelProperty(value = "是否修改密码")
    private Boolean ffIsChangePwd;
    @ApiModelProperty(value = "是否是管理员")
    private Boolean ffIsAdmin;
    @ApiModelProperty(value = "是否禁用")
    private Boolean ffIsDisable;
    @ApiModelProperty(value = "是否在线")
    private Boolean ffIsOnline;
    @ApiModelProperty(value = "是否权限控制")
    private Boolean ffIsLimit;
    @ApiModelProperty(value = "新增时间")
    private Date ffAddDate;
    @ApiModelProperty(value = "修改时间")
    private Date ffModifyDate;
    @ApiModelProperty(value = "头像访问地址")
    private String ffAvatarURL;
    @ApiModelProperty(value = "头像访问地址")
    private Integer ffAvatarID;
    @ApiModelProperty(value = "地区")
    private String ffArea;
    @ApiModelProperty(value = "环信密码")
    private String ffEasemobPwd;
    @ApiModelProperty(value = "是否是测试")
    private Boolean ffIsTest;
    // ----  到时候删除上面部分 ----- //
    @ApiModelProperty(value = "员工信息ID")
    private Integer empID;
    @ApiModelProperty(value = "员工用户名")
    private String userName;
    @ApiModelProperty(value = "员工姓名")
    private String name;
    @ApiModelProperty(value = "性别")
    private Integer sex;
    @ApiModelProperty(value = "生日")
    private Date birthday;
    @ApiModelProperty(value = "入职日期")
    private Date inDate;
    @ApiModelProperty(value = "学历")
    private Integer degrees;
    @ApiModelProperty(value = "身份证号")
    private String idCard;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "身份证地址")
    private String idCardAddress;
    @ApiModelProperty(value = "是否结婚")
    private Integer marital;
    @ApiModelProperty(value = "职位")
    private Integer position;
    @ApiModelProperty(value = "手机")
    private String mobile;
    @ApiModelProperty(value = "备用手机")
    private String spareMobile;
    @ApiModelProperty(value = "部门ID")
    private Integer deptID;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "密码")
    private String pwd;
    @ApiModelProperty(value = "支付密码")
    private String payPwd;
    @ApiModelProperty(value = "是否修改密码")
    private Boolean isChangePwd;
    @ApiModelProperty(value = "是否是管理员")
    private Boolean isAdmin;
    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisable;
    @ApiModelProperty(value = "是否在线")
    private Boolean isOnline;
    @ApiModelProperty(value = "是否权限控制")
    private Boolean isLimit;
    @ApiModelProperty(value = "新增时间")
    private Date addDate;
    @ApiModelProperty(value = "修改时间")
    private Date modifyDate;
    @ApiModelProperty(value = "头像访问地址")
    private String avatarURL;
    @ApiModelProperty(value = "头像访问地址")
    private Integer avatarID;
    @ApiModelProperty(value = "地区")
    private String area;
    @ApiModelProperty(value = "环信密码")
    private String easemobPwd;
    @ApiModelProperty(value = "是否是测试")
    private Boolean isTest;

    public Emp() {
    }

    public Emp(EmpBo empBo) {
        if (empBo != null) {
//            this.ffEmpID = empBo.getEmpID();
//            this.ffUserName = empBo.getUserName();
//            this.ffName = empBo.getName();
//            this.ffSex = empBo.getSex();
//            this.ffBirthday = empBo.getBirthday();
//            this.ffInDate = empBo.getInDate();
//            this.ffDegrees = empBo.getDegrees();
//            this.ffIDCard = empBo.getIDCard();
//            this.ffAddress = empBo.getAddress();
//            this.ffIDCardAddress = empBo.getIDCardAddress();
//            this.ffMarital = empBo.getMarital();
//            this.ffPosition = empBo.getPosition() != null ? empBo.getPosition().getValue() : null;
//            this.ffMobile = empBo.getMobile();
//            this.ffSpareMobile = empBo.getSpareMobile();
//            this.ffDeptID = empBo.getDeptID();
//            this.ffRemark = empBo.getRemark();
//            this.ffPwd = empBo.getPwd();
//            this.ffPayPwd = empBo.getPayPwd();
//            this.ffIsChangePwd = empBo.getIsChangePwd();
//            this.ffIsAdmin = empBo.getIsAdmin();
//            this.ffIsDisable = empBo.getIsDisable();
//            this.ffIsOnline = empBo.getIsOnline();
//            this.ffIsLimit = empBo.getIsLimit();
//            this.ffAddDate = empBo.getAddDate();
//            this.ffModifyDate = empBo.getModifyDate();
//            this.ffAvatarURL = empBo.getAvatarURL();
//            this.ffAvatarID = empBo.getAvatarID();
//            this.ffArea = empBo.getArea();
//            this.ffEasemobPwd = empBo.getEasemobPwd();
//            this.ffIsTest = empBo.getIsTest();
            this.empID = empBo.getEmpID();
            this.userName = empBo.getUserName();
            this.name = empBo.getName();
            this.sex = empBo.getSex();
            this.birthday = empBo.getBirthday();
            this.inDate = empBo.getInDate();
            this.degrees = empBo.getDegrees();
            this.idCard = empBo.getIDCard();
            this.address = empBo.getAddress();
            this.idCardAddress = empBo.getIDCardAddress();
            this.marital = empBo.getMarital();
            this.position = empBo.getPosition() != null ? empBo.getPosition().getValue() : null;
            this.mobile = empBo.getMobile();
            this.spareMobile = empBo.getSpareMobile();
            this.deptID = empBo.getDeptID();
            this.remark = empBo.getRemark();
            this.pwd = empBo.getPwd();
            this.payPwd = empBo.getPayPwd();
            this.isChangePwd = empBo.getIsChangePwd();
            this.isAdmin = empBo.getIsAdmin();
            this.isDisable = empBo.getIsDisable();
            this.isOnline = empBo.getIsOnline();
            this.isLimit = empBo.getIsLimit();
            this.addDate = empBo.getAddDate();
            this.modifyDate = empBo.getModifyDate();
            this.avatarURL = empBo.getAvatarURL();
            this.avatarID = empBo.getAvatarID();
            this.area = empBo.getArea();
            this.easemobPwd = empBo.getEasemobPwd();
            this.isTest = empBo.getIsTest();
        }
    }

    public Integer getFfEmpID() {
        return empID;
    }

    public void setFfEmpID(Integer ffEmpID) {
        this.empID = ffEmpID;
    }

    public String getFfUserName() {
        return userName;
    }

    public void setFfUserName(String ffUserName) {
        this.userName = ffUserName;
    }

    public String getFfName() {
        return name;
    }

    public void setFfName(String ffName) {
        this.name = ffName;
    }

    public Integer getFfSex() {
        return sex;
    }

    public void setFfSex(Integer ffSex) {
        this.sex = ffSex;
    }

    public Date getFfBirthday() {
        return birthday;
    }

    public void setFfBirthday(Date ffBirthday) {
        this.birthday = ffBirthday;
    }

    public Date getFfInDate() {
        return inDate;
    }

    public void setFfInDate(Date ffInDate) {
        this.inDate = ffInDate;
    }

    public Integer getFfDegrees() {
        return degrees;
    }

    public void setFfDegrees(Integer ffDegrees) {
        this.degrees = ffDegrees;
    }

    public String getFfIDCard() {
        return idCard;
    }

    public void setFfIDCard(String ffIDCard) {
        this.idCard = ffIDCard;
    }

    public String getFfAddress() {
        return address;
    }

    public void setFfAddress(String ffAddress) {
        this.address = ffAddress;
    }

    public String getFfIDCardAddress() {
        return idCardAddress;
    }

    public void setFfIDCardAddress(String ffIDCardAddress) {
        this.idCardAddress = ffIDCardAddress;
    }

    public Integer getFfMarital() {
        return marital;
    }

    public void setFfMarital(Integer ffMarital) {
        this.marital = ffMarital;
    }

    public Integer getFfPosition() {
        return position;
    }

    public void setFfPosition(Integer ffPosition) {
        this.position = ffPosition;
    }

    public String getFfMobile() {
        return mobile;
    }

    public void setFfMobile(String ffMobile) {
        this.mobile = ffMobile;
    }

    public String getFfSpareMobile() {
        return spareMobile;
    }

    public void setFfSpareMobile(String ffSpareMobile) {
        this.spareMobile = ffSpareMobile;
    }

    public Integer getFfDeptID() {
        return deptID;
    }

    public void setFfDeptID(Integer ffDeptID) {
        this.deptID = ffDeptID;
    }

    public String getFfRemark() {
        return remark;
    }

    public void setFfRemark(String ffRemark) {
        this.remark = ffRemark;
    }

    public String getFfPwd() {
        return pwd;
    }

    public void setFfPwd(String ffPwd) {
        this.pwd = ffPwd;
    }

    public String getFfPayPwd() {
        return payPwd;
    }

    public void setFfPayPwd(String ffPayPwd) {
        this.payPwd = ffPayPwd;
    }

    public Boolean getFfIsChangePwd() {
        return isChangePwd;
    }

    public void setFfIsChangePwd(Boolean ffIsChangePwd) {
        this.isChangePwd = ffIsChangePwd;
    }

    public Boolean getFfIsAdmin() {
        return isAdmin;
    }

    public void setFfIsAdmin(Boolean ffIsAdmin) {
        this.isAdmin = ffIsAdmin;
    }

    public Boolean getFfIsDisable() {
        return isDisable;
    }

    public void setFfIsDisable(Boolean ffIsDisable) {
        this.isDisable = ffIsDisable;
    }

    public Boolean getFfIsOnline() {
        return isOnline;
    }

    public void setFfIsOnline(Boolean ffIsOnline) {
        this.isOnline = ffIsOnline;
    }

    public Boolean getFfIsLimit() {
        return isLimit;
    }

    public void setFfIsLimit(Boolean ffIsLimit) {
        this.isLimit = ffIsLimit;
    }

    public Date getFfAddDate() {
        return addDate;
    }

    public void setFfAddDate(Date ffAddDate) {
        this.addDate = ffAddDate;
    }

    public Date getFfModifyDate() {
        return modifyDate;
    }

    public void setFfModifyDate(Date ffModifyDate) {
        this.modifyDate = ffModifyDate;
    }

    public String getFfAvatarURL() {
        return avatarURL;
    }

    public void setFfAvatarURL(String ffAvatarURL) {
        this.avatarURL = ffAvatarURL;
    }

    public Integer getFfAvatarID() {
        return avatarID;
    }

    public void setFfAvatarID(Integer ffAvatarID) {
        this.avatarID = ffAvatarID;
    }

    public String getFfArea() {
        return area;
    }

    public void setFfArea(String ffArea) {
        this.area = ffArea;
    }

    public String getFfEasemobPwd() {
        return easemobPwd;
    }

    public void setFfEasemobPwd(String ffEasemobPwd) {
        this.easemobPwd = ffEasemobPwd;
    }

    public Boolean getFfIsTest() {
        return isTest;
    }

    public void setFfIsTest(Boolean ffIsTest) {
        this.isTest = ffIsTest;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Integer getDegrees() {
        return degrees;
    }

    public void setDegrees(Integer degrees) {
        this.degrees = degrees;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCardAddress() {
        return idCardAddress;
    }

    public void setIdCardAddress(String idCardAddress) {
        this.idCardAddress = idCardAddress;
    }

    public Integer getMarital() {
        return marital;
    }

    public void setMarital(Integer marital) {
        this.marital = marital;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSpareMobile() {
        return spareMobile;
    }

    public void setSpareMobile(String spareMobile) {
        this.spareMobile = spareMobile;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }

    public Boolean getChangePwd() {
        return isChangePwd;
    }

    public void setChangePwd(Boolean changePwd) {
        isChangePwd = changePwd;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getDisable() {
        return isDisable;
    }

    public void setDisable(Boolean disable) {
        isDisable = disable;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public Boolean getLimit() {
        return isLimit;
    }

    public void setLimit(Boolean limit) {
        isLimit = limit;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public Integer getAvatarID() {
        return avatarID;
    }

    public void setAvatarID(Integer avatarID) {
        this.avatarID = avatarID;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEasemobPwd() {
        return easemobPwd;
    }

    public void setEasemobPwd(String easemobPwd) {
        this.easemobPwd = easemobPwd;
    }

    public Boolean getTest() {
        return isTest;
    }

    public void setTest(Boolean test) {
        isTest = test;
    }
}
