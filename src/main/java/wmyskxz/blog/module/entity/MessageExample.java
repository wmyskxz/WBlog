package wmyskxz.blog.module.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andNotifyIdIsNull() {
            addCriterion("notify_id is null");
            return (Criteria) this;
        }

        public Criteria andNotifyIdIsNotNull() {
            addCriterion("notify_id is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyIdEqualTo(Long value) {
            addCriterion("notify_id =", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdNotEqualTo(Long value) {
            addCriterion("notify_id <>", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdGreaterThan(Long value) {
            addCriterion("notify_id >", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("notify_id >=", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdLessThan(Long value) {
            addCriterion("notify_id <", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdLessThanOrEqualTo(Long value) {
            addCriterion("notify_id <=", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdIn(List<Long> values) {
            addCriterion("notify_id in", values, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdNotIn(List<Long> values) {
            addCriterion("notify_id not in", values, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdBetween(Long value1, Long value2) {
            addCriterion("notify_id between", value1, value2, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdNotBetween(Long value1, Long value2) {
            addCriterion("notify_id not between", value1, value2, "notifyId");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBySenderIsNull() {
            addCriterion("is_delete_by_sender is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBySenderIsNotNull() {
            addCriterion("is_delete_by_sender is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBySenderEqualTo(Boolean value) {
            addCriterion("is_delete_by_sender =", value, "isDeleteBySender");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBySenderNotEqualTo(Boolean value) {
            addCriterion("is_delete_by_sender <>", value, "isDeleteBySender");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBySenderGreaterThan(Boolean value) {
            addCriterion("is_delete_by_sender >", value, "isDeleteBySender");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBySenderGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete_by_sender >=", value, "isDeleteBySender");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBySenderLessThan(Boolean value) {
            addCriterion("is_delete_by_sender <", value, "isDeleteBySender");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBySenderLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete_by_sender <=", value, "isDeleteBySender");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBySenderIn(List<Boolean> values) {
            addCriterion("is_delete_by_sender in", values, "isDeleteBySender");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBySenderNotIn(List<Boolean> values) {
            addCriterion("is_delete_by_sender not in", values, "isDeleteBySender");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBySenderBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete_by_sender between", value1, value2, "isDeleteBySender");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBySenderNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete_by_sender not between", value1, value2, "isDeleteBySender");
            return (Criteria) this;
        }

        public Criteria andIsDeleteByRecevierIsNull() {
            addCriterion("is_delete_by_recevier is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteByRecevierIsNotNull() {
            addCriterion("is_delete_by_recevier is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteByRecevierEqualTo(Boolean value) {
            addCriterion("is_delete_by_recevier =", value, "isDeleteByRecevier");
            return (Criteria) this;
        }

        public Criteria andIsDeleteByRecevierNotEqualTo(Boolean value) {
            addCriterion("is_delete_by_recevier <>", value, "isDeleteByRecevier");
            return (Criteria) this;
        }

        public Criteria andIsDeleteByRecevierGreaterThan(Boolean value) {
            addCriterion("is_delete_by_recevier >", value, "isDeleteByRecevier");
            return (Criteria) this;
        }

        public Criteria andIsDeleteByRecevierGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete_by_recevier >=", value, "isDeleteByRecevier");
            return (Criteria) this;
        }

        public Criteria andIsDeleteByRecevierLessThan(Boolean value) {
            addCriterion("is_delete_by_recevier <", value, "isDeleteByRecevier");
            return (Criteria) this;
        }

        public Criteria andIsDeleteByRecevierLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete_by_recevier <=", value, "isDeleteByRecevier");
            return (Criteria) this;
        }

        public Criteria andIsDeleteByRecevierIn(List<Boolean> values) {
            addCriterion("is_delete_by_recevier in", values, "isDeleteByRecevier");
            return (Criteria) this;
        }

        public Criteria andIsDeleteByRecevierNotIn(List<Boolean> values) {
            addCriterion("is_delete_by_recevier not in", values, "isDeleteByRecevier");
            return (Criteria) this;
        }

        public Criteria andIsDeleteByRecevierBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete_by_recevier between", value1, value2, "isDeleteByRecevier");
            return (Criteria) this;
        }

        public Criteria andIsDeleteByRecevierNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete_by_recevier not between", value1, value2, "isDeleteByRecevier");
            return (Criteria) this;
        }

        public Criteria andSenderIdIsNull() {
            addCriterion("sender_id is null");
            return (Criteria) this;
        }

        public Criteria andSenderIdIsNotNull() {
            addCriterion("sender_id is not null");
            return (Criteria) this;
        }

        public Criteria andSenderIdEqualTo(Long value) {
            addCriterion("sender_id =", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotEqualTo(Long value) {
            addCriterion("sender_id <>", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdGreaterThan(Long value) {
            addCriterion("sender_id >", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sender_id >=", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLessThan(Long value) {
            addCriterion("sender_id <", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLessThanOrEqualTo(Long value) {
            addCriterion("sender_id <=", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdIn(List<Long> values) {
            addCriterion("sender_id in", values, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotIn(List<Long> values) {
            addCriterion("sender_id not in", values, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdBetween(Long value1, Long value2) {
            addCriterion("sender_id between", value1, value2, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotBetween(Long value1, Long value2) {
            addCriterion("sender_id not between", value1, value2, "senderId");
            return (Criteria) this;
        }

        public Criteria andRecevierIdIsNull() {
            addCriterion("recevier_id is null");
            return (Criteria) this;
        }

        public Criteria andRecevierIdIsNotNull() {
            addCriterion("recevier_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecevierIdEqualTo(Long value) {
            addCriterion("recevier_id =", value, "recevierId");
            return (Criteria) this;
        }

        public Criteria andRecevierIdNotEqualTo(Long value) {
            addCriterion("recevier_id <>", value, "recevierId");
            return (Criteria) this;
        }

        public Criteria andRecevierIdGreaterThan(Long value) {
            addCriterion("recevier_id >", value, "recevierId");
            return (Criteria) this;
        }

        public Criteria andRecevierIdGreaterThanOrEqualTo(Long value) {
            addCriterion("recevier_id >=", value, "recevierId");
            return (Criteria) this;
        }

        public Criteria andRecevierIdLessThan(Long value) {
            addCriterion("recevier_id <", value, "recevierId");
            return (Criteria) this;
        }

        public Criteria andRecevierIdLessThanOrEqualTo(Long value) {
            addCriterion("recevier_id <=", value, "recevierId");
            return (Criteria) this;
        }

        public Criteria andRecevierIdIn(List<Long> values) {
            addCriterion("recevier_id in", values, "recevierId");
            return (Criteria) this;
        }

        public Criteria andRecevierIdNotIn(List<Long> values) {
            addCriterion("recevier_id not in", values, "recevierId");
            return (Criteria) this;
        }

        public Criteria andRecevierIdBetween(Long value1, Long value2) {
            addCriterion("recevier_id between", value1, value2, "recevierId");
            return (Criteria) this;
        }

        public Criteria andRecevierIdNotBetween(Long value1, Long value2) {
            addCriterion("recevier_id not between", value1, value2, "recevierId");
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