package com.xiaoyong.hrm.customer.web;/**
 * Created by atlantisholic on 2018/8/16.
 */

import com.xiaoyong.hrm.customer.domain.Customer;
import com.xiaoyong.hrm.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName CustomerController
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/16 23:18
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.save(customer));
    }

    @PostMapping(value = "/deleteById")
    public ResponseEntity<?> deleteById(int id){
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/deleteByIds")
    public ResponseEntity<?> deleteByIds(@RequestBody Integer[] ids){
        customerService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<?> findById(int id){
        return ResponseEntity.ok(customerService.findById(id));
    }

    @GetMapping(value = "/findList")
    public ResponseEntity<?> findList(Customer customer, @PageableDefault(
            sort = "createTime", direction = Sort.Direction.DESC)Pageable pageable){
        return ResponseEntity.ok(customerService.findByExample(customer, pageable));
    }

}
