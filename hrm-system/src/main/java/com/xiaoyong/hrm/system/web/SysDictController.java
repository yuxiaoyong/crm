package com.xiaoyong.hrm.system.web;/**
 * Created by atlantisholic on 2018/8/7.
 */

import com.xiaoyong.hrm.system.domain.SysDict;
import com.xiaoyong.hrm.system.service.SysDictService;
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
 * @ClassName SysDictController
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/7 21:31
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("/api/dict")
public class SysDictController {

    @Autowired
    SysDictService sysDictService;

    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody SysDict sysDict){
        return ResponseEntity.ok(sysDictService.save(sysDict));
    }

    @PostMapping(value = "/deleteById")
    public ResponseEntity<?> deleteById(int id){
        sysDictService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/deleteByIds")
    public ResponseEntity<?> deleteByIds(@RequestBody Integer[] ids){
        sysDictService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<?> findById(int id){
        return ResponseEntity.ok(sysDictService.findById(id));
    }

    @GetMapping(value = "/findList")
    public ResponseEntity<?> findList(SysDict sysDict, @PageableDefault(
            sort = "dictType", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.ok(sysDictService.findByExample(sysDict, pageable));
    }

}
