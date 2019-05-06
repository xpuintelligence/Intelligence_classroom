package com.smart.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbCourseitemHomeworkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbCourseitemHomeworkExample() {
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

        public Criteria andUploadTimeIsNull() {
            addCriterion("upload_time is null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNotNull() {
            addCriterion("upload_time is not null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeEqualTo(Date value) {
            addCriterion("upload_time =", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotEqualTo(Date value) {
            addCriterion("upload_time <>", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThan(Date value) {
            addCriterion("upload_time >", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upload_time >=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThan(Date value) {
            addCriterion("upload_time <", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThanOrEqualTo(Date value) {
            addCriterion("upload_time <=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIn(List<Date> values) {
            addCriterion("upload_time in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotIn(List<Date> values) {
            addCriterion("upload_time not in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeBetween(Date value1, Date value2) {
            addCriterion("upload_time between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotBetween(Date value1, Date value2) {
            addCriterion("upload_time not between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlIsNull() {
            addCriterion("homework_url is null");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlIsNotNull() {
            addCriterion("homework_url is not null");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlEqualTo(String value) {
            addCriterion("homework_url =", value, "homeworkUrl");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlNotEqualTo(String value) {
            addCriterion("homework_url <>", value, "homeworkUrl");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlGreaterThan(String value) {
            addCriterion("homework_url >", value, "homeworkUrl");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlGreaterThanOrEqualTo(String value) {
            addCriterion("homework_url >=", value, "homeworkUrl");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlLessThan(String value) {
            addCriterion("homework_url <", value, "homeworkUrl");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlLessThanOrEqualTo(String value) {
            addCriterion("homework_url <=", value, "homeworkUrl");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlLike(String value) {
            addCriterion("homework_url like", value, "homeworkUrl");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlNotLike(String value) {
            addCriterion("homework_url not like", value, "homeworkUrl");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlIn(List<String> values) {
            addCriterion("homework_url in", values, "homeworkUrl");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlNotIn(List<String> values) {
            addCriterion("homework_url not in", values, "homeworkUrl");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlBetween(String value1, String value2) {
            addCriterion("homework_url between", value1, value2, "homeworkUrl");
            return (Criteria) this;
        }

        public Criteria andHomeworkUrlNotBetween(String value1, String value2) {
            addCriterion("homework_url not between", value1, value2, "homeworkUrl");
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