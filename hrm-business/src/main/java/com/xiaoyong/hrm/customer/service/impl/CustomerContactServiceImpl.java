package com.xiaoyong.hrm.customer.service.impl;/**
 * Created by atlantisholic on 2018/8/19.
 */

import com.xiaoyong.hrm.customer.domain.Customer;
import com.xiaoyong.hrm.customer.domain.CustomerContact;
import com.xiaoyong.hrm.customer.domain.CustomerProduct;
import com.xiaoyong.hrm.customer.model.CustomerContactQuery;
import com.xiaoyong.hrm.customer.service.CustomerContactService;
import com.xiaoyong.hrm.customer.service.CustomerService;
import com.xiaoyong.hrm.support.service.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CustomerContactServiceImpl
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/19 17:32
 * @Version 1.0.0
 **/
@Service
public class CustomerContactServiceImpl extends BaseServiceImpl<CustomerContact, Integer> implements CustomerContactService {

    @Autowired
    CustomerService customerService;

    @Transactional
    @Override
    public CustomerContact save(CustomerContact entity) {
        Customer customer = customerService.save(entity.getCustomer());
        entity.setCustomer(customer);
        return super.save(entity);
    }

    @Override
    public Page<CustomerContact> findByQuery(CustomerContactQuery model, Pageable pageable) {
        Specification<CustomerContact> specification = new Specification<CustomerContact>() {
            @Override
            public Predicate toPredicate(Root<CustomerContact> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(StringUtils.isNotBlank(model.getKeyword())){
                    predicates.add(cb.or(
                            cb.like(root.<String>get("realname"), "%"+model.getKeyword()+"%"),
                            cb.like(root.<String>get("mobile"), "%"+model.getKeyword()+"%"),
                            cb.like(root.<Customer>get("customer").<String>get("name"), "%"+model.getKeyword()+"%"),
                            cb.like(root.<Customer>get("customer").<String>get("address"), "%"+model.getKeyword()+"%")));
                }
                if(StringUtils.isNotBlank(model.getStatus())){
                    predicates.add(cb.and(cb.equal(root.<Customer>get("customer").<String>get("status"), model.getStatus())));
                }
                if(model.getProductId() != null){
                    Join<Customer, CustomerProduct> listJoin = root.join("customer").join("products");
                    predicates.add(cb.equal(listJoin.<Integer>get("productId"), model.getProductId()));
                }
                predicates.add(cb.and(cb.equal(root.<Boolean>get("deleted"), Boolean.FALSE)));
                return query.where(predicates.toArray(new Predicate[0])).getRestriction();
            }
        };
        return repository.findAll(specification, pageable);
    }
}
