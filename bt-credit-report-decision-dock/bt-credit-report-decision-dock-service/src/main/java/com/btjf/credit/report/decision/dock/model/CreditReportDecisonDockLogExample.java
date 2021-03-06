package com.btjf.credit.report.decision.dock.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditReportDecisonDockLogExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    public CreditReportDecisonDockLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("FID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("FID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("FID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("FID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("FID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("FID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("FID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("FID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("FID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("FID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("FID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("FID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("FProductId is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("FProductId is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("FProductId =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("FProductId <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("FProductId >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("FProductId >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("FProductId <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("FProductId <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("FProductId in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("FProductId not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("FProductId between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("FProductId not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andParmMapIsNull() {
            addCriterion("FParmMap is null");
            return (Criteria) this;
        }

        public Criteria andParmMapIsNotNull() {
            addCriterion("FParmMap is not null");
            return (Criteria) this;
        }

        public Criteria andParmMapEqualTo(String value) {
            addCriterion("FParmMap =", value, "parmMap");
            return (Criteria) this;
        }

        public Criteria andParmMapNotEqualTo(String value) {
            addCriterion("FParmMap <>", value, "parmMap");
            return (Criteria) this;
        }

        public Criteria andParmMapGreaterThan(String value) {
            addCriterion("FParmMap >", value, "parmMap");
            return (Criteria) this;
        }

        public Criteria andParmMapGreaterThanOrEqualTo(String value) {
            addCriterion("FParmMap >=", value, "parmMap");
            return (Criteria) this;
        }

        public Criteria andParmMapLessThan(String value) {
            addCriterion("FParmMap <", value, "parmMap");
            return (Criteria) this;
        }

        public Criteria andParmMapLessThanOrEqualTo(String value) {
            addCriterion("FParmMap <=", value, "parmMap");
            return (Criteria) this;
        }

        public Criteria andParmMapLike(String value) {
            addCriterion("FParmMap like", value, "parmMap");
            return (Criteria) this;
        }

        public Criteria andParmMapNotLike(String value) {
            addCriterion("FParmMap not like", value, "parmMap");
            return (Criteria) this;
        }

        public Criteria andParmMapIn(List<String> values) {
            addCriterion("FParmMap in", values, "parmMap");
            return (Criteria) this;
        }

        public Criteria andParmMapNotIn(List<String> values) {
            addCriterion("FParmMap not in", values, "parmMap");
            return (Criteria) this;
        }

        public Criteria andParmMapBetween(String value1, String value2) {
            addCriterion("FParmMap between", value1, value2, "parmMap");
            return (Criteria) this;
        }

        public Criteria andParmMapNotBetween(String value1, String value2) {
            addCriterion("FParmMap not between", value1, value2, "parmMap");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("FUrl is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("FUrl is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("FUrl =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("FUrl <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("FUrl >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("FUrl >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("FUrl <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("FUrl <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("FUrl like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("FUrl not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("FUrl in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("FUrl not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("FUrl between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("FUrl not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andResultCodeIsNull() {
            addCriterion("FResultCode is null");
            return (Criteria) this;
        }

        public Criteria andResultCodeIsNotNull() {
            addCriterion("FResultCode is not null");
            return (Criteria) this;
        }

        public Criteria andResultCodeEqualTo(String value) {
            addCriterion("FResultCode =", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeNotEqualTo(String value) {
            addCriterion("FResultCode <>", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeGreaterThan(String value) {
            addCriterion("FResultCode >", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FResultCode >=", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeLessThan(String value) {
            addCriterion("FResultCode <", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeLessThanOrEqualTo(String value) {
            addCriterion("FResultCode <=", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeLike(String value) {
            addCriterion("FResultCode like", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeNotLike(String value) {
            addCriterion("FResultCode not like", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeIn(List<String> values) {
            addCriterion("FResultCode in", values, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeNotIn(List<String> values) {
            addCriterion("FResultCode not in", values, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeBetween(String value1, String value2) {
            addCriterion("FResultCode between", value1, value2, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeNotBetween(String value1, String value2) {
            addCriterion("FResultCode not between", value1, value2, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultDescIsNull() {
            addCriterion("FResultDesc is null");
            return (Criteria) this;
        }

        public Criteria andResultDescIsNotNull() {
            addCriterion("FResultDesc is not null");
            return (Criteria) this;
        }

        public Criteria andResultDescEqualTo(String value) {
            addCriterion("FResultDesc =", value, "resultDesc");
            return (Criteria) this;
        }

        public Criteria andResultDescNotEqualTo(String value) {
            addCriterion("FResultDesc <>", value, "resultDesc");
            return (Criteria) this;
        }

        public Criteria andResultDescGreaterThan(String value) {
            addCriterion("FResultDesc >", value, "resultDesc");
            return (Criteria) this;
        }

        public Criteria andResultDescGreaterThanOrEqualTo(String value) {
            addCriterion("FResultDesc >=", value, "resultDesc");
            return (Criteria) this;
        }

        public Criteria andResultDescLessThan(String value) {
            addCriterion("FResultDesc <", value, "resultDesc");
            return (Criteria) this;
        }

        public Criteria andResultDescLessThanOrEqualTo(String value) {
            addCriterion("FResultDesc <=", value, "resultDesc");
            return (Criteria) this;
        }

        public Criteria andResultDescLike(String value) {
            addCriterion("FResultDesc like", value, "resultDesc");
            return (Criteria) this;
        }

        public Criteria andResultDescNotLike(String value) {
            addCriterion("FResultDesc not like", value, "resultDesc");
            return (Criteria) this;
        }

        public Criteria andResultDescIn(List<String> values) {
            addCriterion("FResultDesc in", values, "resultDesc");
            return (Criteria) this;
        }

        public Criteria andResultDescNotIn(List<String> values) {
            addCriterion("FResultDesc not in", values, "resultDesc");
            return (Criteria) this;
        }

        public Criteria andResultDescBetween(String value1, String value2) {
            addCriterion("FResultDesc between", value1, value2, "resultDesc");
            return (Criteria) this;
        }

        public Criteria andResultDescNotBetween(String value1, String value2) {
            addCriterion("FResultDesc not between", value1, value2, "resultDesc");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("FStartTime is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("FStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("FStartTime =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("FStartTime <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("FStartTime >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("FStartTime >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("FStartTime <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("FStartTime <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("FStartTime in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("FStartTime not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("FStartTime between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("FStartTime not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("FEndTime is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("FEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("FEndTime =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("FEndTime <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("FEndTime >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("FEndTime >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("FEndTime <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("FEndTime <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("FEndTime in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("FEndTime not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("FEndTime between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("FEndTime not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("FIsDelete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("FIsDelete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("FIsDelete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("FIsDelete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("FIsDelete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("FIsDelete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("FIsDelete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("FIsDelete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("FIsDelete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("FIsDelete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("FIsDelete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("FIsDelete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("FCreateTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("FCreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("FCreateTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("FCreateTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("FCreateTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("FCreateTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("FCreateTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("FCreateTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("FCreateTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("FCreateTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("FCreateTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("FCreateTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andReportDescIsNull() {
            addCriterion("FReportDesc is null");
            return (Criteria) this;
        }

        public Criteria andReportDescIsNotNull() {
            addCriterion("FReportDesc is not null");
            return (Criteria) this;
        }

        public Criteria andReportDescEqualTo(String value) {
            addCriterion("FReportDesc =", value, "reportDesc");
            return (Criteria) this;
        }

        public Criteria andReportDescNotEqualTo(String value) {
            addCriterion("FReportDesc <>", value, "reportDesc");
            return (Criteria) this;
        }

        public Criteria andReportDescGreaterThan(String value) {
            addCriterion("FReportDesc >", value, "reportDesc");
            return (Criteria) this;
        }

        public Criteria andReportDescGreaterThanOrEqualTo(String value) {
            addCriterion("FReportDesc >=", value, "reportDesc");
            return (Criteria) this;
        }

        public Criteria andReportDescLessThan(String value) {
            addCriterion("FReportDesc <", value, "reportDesc");
            return (Criteria) this;
        }

        public Criteria andReportDescLessThanOrEqualTo(String value) {
            addCriterion("FReportDesc <=", value, "reportDesc");
            return (Criteria) this;
        }

        public Criteria andReportDescLike(String value) {
            addCriterion("FReportDesc like", value, "reportDesc");
            return (Criteria) this;
        }

        public Criteria andReportDescNotLike(String value) {
            addCriterion("FReportDesc not like", value, "reportDesc");
            return (Criteria) this;
        }

        public Criteria andReportDescIn(List<String> values) {
            addCriterion("FReportDesc in", values, "reportDesc");
            return (Criteria) this;
        }

        public Criteria andReportDescNotIn(List<String> values) {
            addCriterion("FReportDesc not in", values, "reportDesc");
            return (Criteria) this;
        }

        public Criteria andReportDescBetween(String value1, String value2) {
            addCriterion("FReportDesc between", value1, value2, "reportDesc");
            return (Criteria) this;
        }

        public Criteria andReportDescNotBetween(String value1, String value2) {
            addCriterion("FReportDesc not between", value1, value2, "reportDesc");
            return (Criteria) this;
        }

        public Criteria andParmMapLikeInsensitive(String value) {
            addCriterion("upper(FParmMap) like", value.toUpperCase(), "parmMap");
            return (Criteria) this;
        }

        public Criteria andUrlLikeInsensitive(String value) {
            addCriterion("upper(FUrl) like", value.toUpperCase(), "url");
            return (Criteria) this;
        }

        public Criteria andResultCodeLikeInsensitive(String value) {
            addCriterion("upper(FResultCode) like", value.toUpperCase(), "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultDescLikeInsensitive(String value) {
            addCriterion("upper(FResultDesc) like", value.toUpperCase(), "resultDesc");
            return (Criteria) this;
        }

        public Criteria andReportDescLikeInsensitive(String value) {
            addCriterion("upper(FReportDesc) like", value.toUpperCase(), "reportDesc");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_CreditReportDecisonDockLog
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}