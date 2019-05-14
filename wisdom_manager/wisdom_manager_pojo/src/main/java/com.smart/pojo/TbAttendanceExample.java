package com.smart.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbAttendanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbAttendanceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andAttendanceIdIsNull() {
            addCriterion("attendance_id is null");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdIsNotNull() {
            addCriterion("attendance_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdEqualTo(String value) {
            addCriterion("attendance_id =", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdNotEqualTo(String value) {
            addCriterion("attendance_id <>", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdGreaterThan(String value) {
            addCriterion("attendance_id >", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("attendance_id >=", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdLessThan(String value) {
            addCriterion("attendance_id <", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdLessThanOrEqualTo(String value) {
            addCriterion("attendance_id <=", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdLike(String value) {
            addCriterion("attendance_id like", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdNotLike(String value) {
            addCriterion("attendance_id not like", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdIn(List<String> values) {
            addCriterion("attendance_id in", values, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdNotIn(List<String> values) {
            addCriterion("attendance_id not in", values, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdBetween(String value1, String value2) {
            addCriterion("attendance_id between", value1, value2, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdNotBetween(String value1, String value2) {
            addCriterion("attendance_id not between", value1, value2, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdIsNull() {
            addCriterion("courseitem_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdIsNotNull() {
            addCriterion("courseitem_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdEqualTo(String value) {
            addCriterion("courseitem_id =", value, "courseitemId");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdNotEqualTo(String value) {
            addCriterion("courseitem_id <>", value, "courseitemId");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdGreaterThan(String value) {
            addCriterion("courseitem_id >", value, "courseitemId");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdGreaterThanOrEqualTo(String value) {
            addCriterion("courseitem_id >=", value, "courseitemId");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdLessThan(String value) {
            addCriterion("courseitem_id <", value, "courseitemId");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdLessThanOrEqualTo(String value) {
            addCriterion("courseitem_id <=", value, "courseitemId");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdLike(String value) {
            addCriterion("courseitem_id like", value, "courseitemId");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdNotLike(String value) {
            addCriterion("courseitem_id not like", value, "courseitemId");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdIn(List<String> values) {
            addCriterion("courseitem_id in", values, "courseitemId");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdNotIn(List<String> values) {
            addCriterion("courseitem_id not in", values, "courseitemId");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdBetween(String value1, String value2) {
            addCriterion("courseitem_id between", value1, value2, "courseitemId");
            return (Criteria) this;
        }

        public Criteria andCourseitemIdNotBetween(String value1, String value2) {
            addCriterion("courseitem_id not between", value1, value2, "courseitemId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(String value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(String value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(String value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(String value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(String value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLike(String value) {
            addCriterion("student_id like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotLike(String value) {
            addCriterion("student_id not like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<String> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<String> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(String value1, String value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(String value1, String value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andHeadUpRateIsNull() {
            addCriterion("head_up_rate is null");
            return (Criteria) this;
        }

        public Criteria andHeadUpRateIsNotNull() {
            addCriterion("head_up_rate is not null");
            return (Criteria) this;
        }

        public Criteria andHeadUpRateEqualTo(Double value) {
            addCriterion("head_up_rate =", value, "headUpRate");
            return (Criteria) this;
        }

        public Criteria andHeadUpRateNotEqualTo(Double value) {
            addCriterion("head_up_rate <>", value, "headUpRate");
            return (Criteria) this;
        }

        public Criteria andHeadUpRateGreaterThan(Double value) {
            addCriterion("head_up_rate >", value, "headUpRate");
            return (Criteria) this;
        }

        public Criteria andHeadUpRateGreaterThanOrEqualTo(Double value) {
            addCriterion("head_up_rate >=", value, "headUpRate");
            return (Criteria) this;
        }

        public Criteria andHeadUpRateLessThan(Double value) {
            addCriterion("head_up_rate <", value, "headUpRate");
            return (Criteria) this;
        }

        public Criteria andHeadUpRateLessThanOrEqualTo(Double value) {
            addCriterion("head_up_rate <=", value, "headUpRate");
            return (Criteria) this;
        }

        public Criteria andHeadUpRateIn(List<Double> values) {
            addCriterion("head_up_rate in", values, "headUpRate");
            return (Criteria) this;
        }

        public Criteria andHeadUpRateNotIn(List<Double> values) {
            addCriterion("head_up_rate not in", values, "headUpRate");
            return (Criteria) this;
        }

        public Criteria andHeadUpRateBetween(Double value1, Double value2) {
            addCriterion("head_up_rate between", value1, value2, "headUpRate");
            return (Criteria) this;
        }

        public Criteria andHeadUpRateNotBetween(Double value1, Double value2) {
            addCriterion("head_up_rate not between", value1, value2, "headUpRate");
            return (Criteria) this;
        }

        public Criteria andGoalIsNull() {
            addCriterion("goal is null");
            return (Criteria) this;
        }

        public Criteria andGoalIsNotNull() {
            addCriterion("goal is not null");
            return (Criteria) this;
        }

        public Criteria andGoalEqualTo(Integer value) {
            addCriterion("goal =", value, "goal");
            return (Criteria) this;
        }

        public Criteria andGoalNotEqualTo(Integer value) {
            addCriterion("goal <>", value, "goal");
            return (Criteria) this;
        }

        public Criteria andGoalGreaterThan(Integer value) {
            addCriterion("goal >", value, "goal");
            return (Criteria) this;
        }

        public Criteria andGoalGreaterThanOrEqualTo(Integer value) {
            addCriterion("goal >=", value, "goal");
            return (Criteria) this;
        }

        public Criteria andGoalLessThan(Integer value) {
            addCriterion("goal <", value, "goal");
            return (Criteria) this;
        }

        public Criteria andGoalLessThanOrEqualTo(Integer value) {
            addCriterion("goal <=", value, "goal");
            return (Criteria) this;
        }

        public Criteria andGoalIn(List<Integer> values) {
            addCriterion("goal in", values, "goal");
            return (Criteria) this;
        }

        public Criteria andGoalNotIn(List<Integer> values) {
            addCriterion("goal not in", values, "goal");
            return (Criteria) this;
        }

        public Criteria andGoalBetween(Integer value1, Integer value2) {
            addCriterion("goal between", value1, value2, "goal");
            return (Criteria) this;
        }

        public Criteria andGoalNotBetween(Integer value1, Integer value2) {
            addCriterion("goal not between", value1, value2, "goal");
            return (Criteria) this;
        }

        public Criteria andSleepIsNull() {
            addCriterion("sleep is null");
            return (Criteria) this;
        }

        public Criteria andSleepIsNotNull() {
            addCriterion("sleep is not null");
            return (Criteria) this;
        }

        public Criteria andSleepEqualTo(String value) {
            addCriterion("sleep =", value, "sleep");
            return (Criteria) this;
        }

        public Criteria andSleepNotEqualTo(String value) {
            addCriterion("sleep <>", value, "sleep");
            return (Criteria) this;
        }

        public Criteria andSleepGreaterThan(String value) {
            addCriterion("sleep >", value, "sleep");
            return (Criteria) this;
        }

        public Criteria andSleepGreaterThanOrEqualTo(String value) {
            addCriterion("sleep >=", value, "sleep");
            return (Criteria) this;
        }

        public Criteria andSleepLessThan(String value) {
            addCriterion("sleep <", value, "sleep");
            return (Criteria) this;
        }

        public Criteria andSleepLessThanOrEqualTo(String value) {
            addCriterion("sleep <=", value, "sleep");
            return (Criteria) this;
        }

        public Criteria andSleepLike(String value) {
            addCriterion("sleep like", value, "sleep");
            return (Criteria) this;
        }

        public Criteria andSleepNotLike(String value) {
            addCriterion("sleep not like", value, "sleep");
            return (Criteria) this;
        }

        public Criteria andSleepIn(List<String> values) {
            addCriterion("sleep in", values, "sleep");
            return (Criteria) this;
        }

        public Criteria andSleepNotIn(List<String> values) {
            addCriterion("sleep not in", values, "sleep");
            return (Criteria) this;
        }

        public Criteria andSleepBetween(String value1, String value2) {
            addCriterion("sleep between", value1, value2, "sleep");
            return (Criteria) this;
        }

        public Criteria andSleepNotBetween(String value1, String value2) {
            addCriterion("sleep not between", value1, value2, "sleep");
            return (Criteria) this;
        }

        public Criteria andHeadupScoreIsNull() {
            addCriterion("headup_score is null");
            return (Criteria) this;
        }

        public Criteria andHeadupScoreIsNotNull() {
            addCriterion("headup_score is not null");
            return (Criteria) this;
        }

        public Criteria andHeadupScoreEqualTo(Integer value) {
            addCriterion("headup_score =", value, "headupScore");
            return (Criteria) this;
        }

        public Criteria andHeadupScoreNotEqualTo(Integer value) {
            addCriterion("headup_score <>", value, "headupScore");
            return (Criteria) this;
        }

        public Criteria andHeadupScoreGreaterThan(Integer value) {
            addCriterion("headup_score >", value, "headupScore");
            return (Criteria) this;
        }

        public Criteria andHeadupScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("headup_score >=", value, "headupScore");
            return (Criteria) this;
        }

        public Criteria andHeadupScoreLessThan(Integer value) {
            addCriterion("headup_score <", value, "headupScore");
            return (Criteria) this;
        }

        public Criteria andHeadupScoreLessThanOrEqualTo(Integer value) {
            addCriterion("headup_score <=", value, "headupScore");
            return (Criteria) this;
        }

        public Criteria andHeadupScoreIn(List<Integer> values) {
            addCriterion("headup_score in", values, "headupScore");
            return (Criteria) this;
        }

        public Criteria andHeadupScoreNotIn(List<Integer> values) {
            addCriterion("headup_score not in", values, "headupScore");
            return (Criteria) this;
        }

        public Criteria andHeadupScoreBetween(Integer value1, Integer value2) {
            addCriterion("headup_score between", value1, value2, "headupScore");
            return (Criteria) this;
        }

        public Criteria andHeadupScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("headup_score not between", value1, value2, "headupScore");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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