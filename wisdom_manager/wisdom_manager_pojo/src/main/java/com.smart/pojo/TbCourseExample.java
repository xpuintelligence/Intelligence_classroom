package com.smart.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbCourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbCourseExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(Integer value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(Integer value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(Integer value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(Integer value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(Integer value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<Integer> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<Integer> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(Integer value1, Integer value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(Integer value1, Integer value2) {
            addCriterion("year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andSemeterIsNull() {
            addCriterion("semeter is null");
            return (Criteria) this;
        }

        public Criteria andSemeterIsNotNull() {
            addCriterion("semeter is not null");
            return (Criteria) this;
        }

        public Criteria andSemeterEqualTo(String value) {
            addCriterion("semeter =", value, "semeter");
            return (Criteria) this;
        }

        public Criteria andSemeterNotEqualTo(String value) {
            addCriterion("semeter <>", value, "semeter");
            return (Criteria) this;
        }

        public Criteria andSemeterGreaterThan(String value) {
            addCriterion("semeter >", value, "semeter");
            return (Criteria) this;
        }

        public Criteria andSemeterGreaterThanOrEqualTo(String value) {
            addCriterion("semeter >=", value, "semeter");
            return (Criteria) this;
        }

        public Criteria andSemeterLessThan(String value) {
            addCriterion("semeter <", value, "semeter");
            return (Criteria) this;
        }

        public Criteria andSemeterLessThanOrEqualTo(String value) {
            addCriterion("semeter <=", value, "semeter");
            return (Criteria) this;
        }

        public Criteria andSemeterLike(String value) {
            addCriterion("semeter like", value, "semeter");
            return (Criteria) this;
        }

        public Criteria andSemeterNotLike(String value) {
            addCriterion("semeter not like", value, "semeter");
            return (Criteria) this;
        }

        public Criteria andSemeterIn(List<String> values) {
            addCriterion("semeter in", values, "semeter");
            return (Criteria) this;
        }

        public Criteria andSemeterNotIn(List<String> values) {
            addCriterion("semeter not in", values, "semeter");
            return (Criteria) this;
        }

        public Criteria andSemeterBetween(String value1, String value2) {
            addCriterion("semeter between", value1, value2, "semeter");
            return (Criteria) this;
        }

        public Criteria andSemeterNotBetween(String value1, String value2) {
            addCriterion("semeter not between", value1, value2, "semeter");
            return (Criteria) this;
        }

        public Criteria andCourseNoteIsNull() {
            addCriterion("course_note is null");
            return (Criteria) this;
        }

        public Criteria andCourseNoteIsNotNull() {
            addCriterion("course_note is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNoteEqualTo(String value) {
            addCriterion("course_note =", value, "courseNote");
            return (Criteria) this;
        }

        public Criteria andCourseNoteNotEqualTo(String value) {
            addCriterion("course_note <>", value, "courseNote");
            return (Criteria) this;
        }

        public Criteria andCourseNoteGreaterThan(String value) {
            addCriterion("course_note >", value, "courseNote");
            return (Criteria) this;
        }

        public Criteria andCourseNoteGreaterThanOrEqualTo(String value) {
            addCriterion("course_note >=", value, "courseNote");
            return (Criteria) this;
        }

        public Criteria andCourseNoteLessThan(String value) {
            addCriterion("course_note <", value, "courseNote");
            return (Criteria) this;
        }

        public Criteria andCourseNoteLessThanOrEqualTo(String value) {
            addCriterion("course_note <=", value, "courseNote");
            return (Criteria) this;
        }

        public Criteria andCourseNoteLike(String value) {
            addCriterion("course_note like", value, "courseNote");
            return (Criteria) this;
        }

        public Criteria andCourseNoteNotLike(String value) {
            addCriterion("course_note not like", value, "courseNote");
            return (Criteria) this;
        }

        public Criteria andCourseNoteIn(List<String> values) {
            addCriterion("course_note in", values, "courseNote");
            return (Criteria) this;
        }

        public Criteria andCourseNoteNotIn(List<String> values) {
            addCriterion("course_note not in", values, "courseNote");
            return (Criteria) this;
        }

        public Criteria andCourseNoteBetween(String value1, String value2) {
            addCriterion("course_note between", value1, value2, "courseNote");
            return (Criteria) this;
        }

        public Criteria andCourseNoteNotBetween(String value1, String value2) {
            addCriterion("course_note not between", value1, value2, "courseNote");
            return (Criteria) this;
        }

        public Criteria andAttendanceTotalScoreIsNull() {
            addCriterion("attendance_total_score is null");
            return (Criteria) this;
        }

        public Criteria andAttendanceTotalScoreIsNotNull() {
            addCriterion("attendance_total_score is not null");
            return (Criteria) this;
        }

        public Criteria andAttendanceTotalScoreEqualTo(Integer value) {
            addCriterion("attendance_total_score =", value, "attendanceTotalScore");
            return (Criteria) this;
        }

        public Criteria andAttendanceTotalScoreNotEqualTo(Integer value) {
            addCriterion("attendance_total_score <>", value, "attendanceTotalScore");
            return (Criteria) this;
        }

        public Criteria andAttendanceTotalScoreGreaterThan(Integer value) {
            addCriterion("attendance_total_score >", value, "attendanceTotalScore");
            return (Criteria) this;
        }

        public Criteria andAttendanceTotalScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("attendance_total_score >=", value, "attendanceTotalScore");
            return (Criteria) this;
        }

        public Criteria andAttendanceTotalScoreLessThan(Integer value) {
            addCriterion("attendance_total_score <", value, "attendanceTotalScore");
            return (Criteria) this;
        }

        public Criteria andAttendanceTotalScoreLessThanOrEqualTo(Integer value) {
            addCriterion("attendance_total_score <=", value, "attendanceTotalScore");
            return (Criteria) this;
        }

        public Criteria andAttendanceTotalScoreIn(List<Integer> values) {
            addCriterion("attendance_total_score in", values, "attendanceTotalScore");
            return (Criteria) this;
        }

        public Criteria andAttendanceTotalScoreNotIn(List<Integer> values) {
            addCriterion("attendance_total_score not in", values, "attendanceTotalScore");
            return (Criteria) this;
        }

        public Criteria andAttendanceTotalScoreBetween(Integer value1, Integer value2) {
            addCriterion("attendance_total_score between", value1, value2, "attendanceTotalScore");
            return (Criteria) this;
        }

        public Criteria andAttendanceTotalScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("attendance_total_score not between", value1, value2, "attendanceTotalScore");
            return (Criteria) this;
        }

        public Criteria andLeaveScoreIsNull() {
            addCriterion("leave_score is null");
            return (Criteria) this;
        }

        public Criteria andLeaveScoreIsNotNull() {
            addCriterion("leave_score is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveScoreEqualTo(Integer value) {
            addCriterion("leave_score =", value, "leaveScore");
            return (Criteria) this;
        }

        public Criteria andLeaveScoreNotEqualTo(Integer value) {
            addCriterion("leave_score <>", value, "leaveScore");
            return (Criteria) this;
        }

        public Criteria andLeaveScoreGreaterThan(Integer value) {
            addCriterion("leave_score >", value, "leaveScore");
            return (Criteria) this;
        }

        public Criteria andLeaveScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("leave_score >=", value, "leaveScore");
            return (Criteria) this;
        }

        public Criteria andLeaveScoreLessThan(Integer value) {
            addCriterion("leave_score <", value, "leaveScore");
            return (Criteria) this;
        }

        public Criteria andLeaveScoreLessThanOrEqualTo(Integer value) {
            addCriterion("leave_score <=", value, "leaveScore");
            return (Criteria) this;
        }

        public Criteria andLeaveScoreIn(List<Integer> values) {
            addCriterion("leave_score in", values, "leaveScore");
            return (Criteria) this;
        }

        public Criteria andLeaveScoreNotIn(List<Integer> values) {
            addCriterion("leave_score not in", values, "leaveScore");
            return (Criteria) this;
        }

        public Criteria andLeaveScoreBetween(Integer value1, Integer value2) {
            addCriterion("leave_score between", value1, value2, "leaveScore");
            return (Criteria) this;
        }

        public Criteria andLeaveScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("leave_score not between", value1, value2, "leaveScore");
            return (Criteria) this;
        }

        public Criteria andAttendScoreIsNull() {
            addCriterion("attend_score is null");
            return (Criteria) this;
        }

        public Criteria andAttendScoreIsNotNull() {
            addCriterion("attend_score is not null");
            return (Criteria) this;
        }

        public Criteria andAttendScoreEqualTo(Integer value) {
            addCriterion("attend_score =", value, "attendScore");
            return (Criteria) this;
        }

        public Criteria andAttendScoreNotEqualTo(Integer value) {
            addCriterion("attend_score <>", value, "attendScore");
            return (Criteria) this;
        }

        public Criteria andAttendScoreGreaterThan(Integer value) {
            addCriterion("attend_score >", value, "attendScore");
            return (Criteria) this;
        }

        public Criteria andAttendScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("attend_score >=", value, "attendScore");
            return (Criteria) this;
        }

        public Criteria andAttendScoreLessThan(Integer value) {
            addCriterion("attend_score <", value, "attendScore");
            return (Criteria) this;
        }

        public Criteria andAttendScoreLessThanOrEqualTo(Integer value) {
            addCriterion("attend_score <=", value, "attendScore");
            return (Criteria) this;
        }

        public Criteria andAttendScoreIn(List<Integer> values) {
            addCriterion("attend_score in", values, "attendScore");
            return (Criteria) this;
        }

        public Criteria andAttendScoreNotIn(List<Integer> values) {
            addCriterion("attend_score not in", values, "attendScore");
            return (Criteria) this;
        }

        public Criteria andAttendScoreBetween(Integer value1, Integer value2) {
            addCriterion("attend_score between", value1, value2, "attendScore");
            return (Criteria) this;
        }

        public Criteria andAttendScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("attend_score not between", value1, value2, "attendScore");
            return (Criteria) this;
        }

        public Criteria andLateAttendScoreIsNull() {
            addCriterion("late_attend_score is null");
            return (Criteria) this;
        }

        public Criteria andLateAttendScoreIsNotNull() {
            addCriterion("late_attend_score is not null");
            return (Criteria) this;
        }

        public Criteria andLateAttendScoreEqualTo(Integer value) {
            addCriterion("late_attend_score =", value, "lateAttendScore");
            return (Criteria) this;
        }

        public Criteria andLateAttendScoreNotEqualTo(Integer value) {
            addCriterion("late_attend_score <>", value, "lateAttendScore");
            return (Criteria) this;
        }

        public Criteria andLateAttendScoreGreaterThan(Integer value) {
            addCriterion("late_attend_score >", value, "lateAttendScore");
            return (Criteria) this;
        }

        public Criteria andLateAttendScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("late_attend_score >=", value, "lateAttendScore");
            return (Criteria) this;
        }

        public Criteria andLateAttendScoreLessThan(Integer value) {
            addCriterion("late_attend_score <", value, "lateAttendScore");
            return (Criteria) this;
        }

        public Criteria andLateAttendScoreLessThanOrEqualTo(Integer value) {
            addCriterion("late_attend_score <=", value, "lateAttendScore");
            return (Criteria) this;
        }

        public Criteria andLateAttendScoreIn(List<Integer> values) {
            addCriterion("late_attend_score in", values, "lateAttendScore");
            return (Criteria) this;
        }

        public Criteria andLateAttendScoreNotIn(List<Integer> values) {
            addCriterion("late_attend_score not in", values, "lateAttendScore");
            return (Criteria) this;
        }

        public Criteria andLateAttendScoreBetween(Integer value1, Integer value2) {
            addCriterion("late_attend_score between", value1, value2, "lateAttendScore");
            return (Criteria) this;
        }

        public Criteria andLateAttendScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("late_attend_score not between", value1, value2, "lateAttendScore");
            return (Criteria) this;
        }

        public Criteria andHeadUpScoreIsNull() {
            addCriterion("head_up_score is null");
            return (Criteria) this;
        }

        public Criteria andHeadUpScoreIsNotNull() {
            addCriterion("head_up_score is not null");
            return (Criteria) this;
        }

        public Criteria andHeadUpScoreEqualTo(Integer value) {
            addCriterion("head_up_score =", value, "headUpScore");
            return (Criteria) this;
        }

        public Criteria andHeadUpScoreNotEqualTo(Integer value) {
            addCriterion("head_up_score <>", value, "headUpScore");
            return (Criteria) this;
        }

        public Criteria andHeadUpScoreGreaterThan(Integer value) {
            addCriterion("head_up_score >", value, "headUpScore");
            return (Criteria) this;
        }

        public Criteria andHeadUpScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("head_up_score >=", value, "headUpScore");
            return (Criteria) this;
        }

        public Criteria andHeadUpScoreLessThan(Integer value) {
            addCriterion("head_up_score <", value, "headUpScore");
            return (Criteria) this;
        }

        public Criteria andHeadUpScoreLessThanOrEqualTo(Integer value) {
            addCriterion("head_up_score <=", value, "headUpScore");
            return (Criteria) this;
        }

        public Criteria andHeadUpScoreIn(List<Integer> values) {
            addCriterion("head_up_score in", values, "headUpScore");
            return (Criteria) this;
        }

        public Criteria andHeadUpScoreNotIn(List<Integer> values) {
            addCriterion("head_up_score not in", values, "headUpScore");
            return (Criteria) this;
        }

        public Criteria andHeadUpScoreBetween(Integer value1, Integer value2) {
            addCriterion("head_up_score between", value1, value2, "headUpScore");
            return (Criteria) this;
        }

        public Criteria andHeadUpScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("head_up_score not between", value1, value2, "headUpScore");
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