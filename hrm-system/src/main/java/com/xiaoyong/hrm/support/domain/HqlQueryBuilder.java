package com.xiaoyong.hrm.support.domain;/**
 * Created by atlantisholic on 2018/9/28.
 */

import com.google.common.collect.ImmutableMap;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName HqlQueryBuilder
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/28 21:32
 * @Version 1.0.0
 **/
public class HqlQueryBuilder {

    private final Map<String, String> operatorMap = ImmutableMap.<String, String>builder()
            .put("LIKE", "like")
            .put("NOT_LIKE", "not like")
            .put("EQ", "=")
            .put("NOT_EQ", "<>")
            .put("IN", "in")
            .put("NOT_IN", "not in")
            .put("GT", ">")
            .put("GTE", ">=")
            .put("LT", "<")
            .put("LTE", "<=")
            .put("NULL", "is null")
            .put("NOT_NULL", "is not null").build();

    private EntityManager entityManager;

    public HqlQueryBuilder(){}

    public HqlQueryBuilder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public TypedQuery<Long> buildCountQuery(Class clazz, Map<String, Object> dslParamMap) {
        StringBuffer hql = new StringBuffer("select count(*) from " + clazz.getSimpleName() + " t1 where 1=1");
        List<Condition> conditions = getQueryConditions(dslParamMap);
        String whereFragment = buildHQLWhereFragment(conditions);
        TypedQuery<Long> query = entityManager.createQuery(hql.toString() + whereFragment, Long.class);
        conditions.forEach( (condition)-> {
            if(condition.placeholder != null){
                query.setParameter(condition.placeholder, condition.value);
            }
        } );
        return query;
    }

    public Query buildListQuery(Class clazz, Map<String, Object> dslParamMap, Pageable pageable) {
        StringBuffer hql = new StringBuffer("from " + clazz.getSimpleName() + " t1 where 1 = 1 ");
        List<Condition> conditions = getQueryConditions(dslParamMap);
        String whereFragment = buildHQLWhereFragment(conditions);
        String orderFragment = buildHQLOrderFragment(pageable);
        Query query = entityManager.createQuery(hql.toString() + whereFragment + orderFragment);
        conditions.forEach( (condition)-> {
            if(condition.placeholder != null){
                query.setParameter(condition.placeholder, condition.value);
            }
        } );
        if (pageable != null) {
            return query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize()).setMaxResults(pageable.getPageSize());
        }
        return query;
    }

    private String buildHQLOrderFragment(Pageable pageable){
        if(pageable == null || pageable.getSort() == null){
            return "";
        }
        String prefix = " order by ";
        StringBuilder hql = new StringBuilder(prefix);
        pageable.getSort().forEach( ( order ) -> {
            if(hql.toString().equals(prefix)) {
                hql.append(order.getProperty() + " " + order.getDirection().name());
            }else{
                hql.append("," + order.getProperty() + " " + order.getDirection().name());
            }
        });

        return hql.toString();
    }

    private String buildHQLWhereFragment(List<Condition> conditions){
        int i = 0;
        StringBuilder hql = new StringBuilder();
        for(Condition condition: conditions){

            if(condition.value == null) continue;

            String placeholder = "parameter" + i++;
            switch(condition.operator){
                case "LIKE":
                case "NOT_LIKE":
                case "EQ":
                case "NOT_EQ":
                case "IN":
                case "NOT_IN":
                case "GT":
                case "GTE":
                case "LT":
                case "LTE":
                    hql.append(" and " + condition.paramName + " " + operatorMap.get(condition.operator) + ":" + placeholder);
                    condition.placeholder = placeholder;
                    break;
                case "NULL":
                case "NOT_NULL":
                    hql.append(" and " + condition.paramName + " " + operatorMap.get(condition.operator) + ":" + placeholder);
                    break;
            }
        }
        return hql.toString();
    }

    private List<Condition> getQueryConditions(Map<String, Object> dslParamMap){
        List<Condition> conditions = new ArrayList<>(8);
        Pattern pattern = Pattern.compile("(.+)\\((.+)\\)", Pattern.CASE_INSENSITIVE);
        for(String key: dslParamMap.keySet()){
            Matcher matcher = pattern.matcher(key);
            if(matcher.groupCount() != 2 || !matcher.find()){
                continue;
            }
            conditions.add(new Condition(matcher.group(2), matcher.group(1).toUpperCase(), dslParamMap.get(key)));
        }
        return conditions;
    }

    static class Condition{
        //参数路径
        private String paramName;
        //操作符
        private String operator;
        //参数值
        private Object value;
        //参数占位符，默认值为空，只有在用到时才不为空
        private String placeholder;

        public Condition(String paramName, String operator, Object value) {
            this.paramName = paramName;
            this.operator = operator;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Condition{" +
                    "paramName='" + paramName + '\'' +
                    ", operator='" + operator + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

}
