package com.xiaoyong.hrm.customer.web;/**
 * Created by atlantisholic on 2018/8/24.
 */

import com.xiaoyong.hrm.customer.domain.CustomerFollow;
import com.xiaoyong.hrm.customer.service.CustomerFollowService;
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
 * @ClassName CustomerFollowController
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/24 9:24
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("/api/follow")
public class CustomerFollowController {

    @Autowired
    CustomerFollowService customerFollowService;

    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody CustomerFollow follow){
        return ResponseEntity.ok(customerFollowService.save(follow));
    }

    @PostMapping(value = "/deleteById")
    public ResponseEntity<?> deleteById(int id){
        customerFollowService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/deleteByIds")
    public ResponseEntity<?> deleteByIds(@RequestBody Integer[] ids){
        customerFollowService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<?> findById(int id){
        return ResponseEntity.ok(customerFollowService.findById(id));
    }

    @GetMapping(value = "/findList")
    public ResponseEntity<?> findList(CustomerFollow follow, @PageableDefault(
            sort = "createTime", direction = Sort.Direction.DESC)Pageable pageable){
        return ResponseEntity.ok(customerFollowService.findByExample(follow, pageable));
    }

    @GetMapping(value = "/getLastFollow")
    public ResponseEntity<?> getLastFollow(int contactId){
        return ResponseEntity.ok(customerFollowService.findLastFollow(contactId));
    }

}
