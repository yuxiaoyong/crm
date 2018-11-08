package com.xiaoyong.hrm.support.web;/**
 * Created by atlantisholic on 2018/9/6.
 */

import com.xiaoyong.hrm.support.domain.BaseEntity;
import com.xiaoyong.hrm.support.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/6 22:40
 * @Version 1.0.0
 **/
public class BaseController<T extends BaseEntity> {

    @Autowired
    protected BaseService<T, Integer> baseService;

    //@ApiOperation("保存信息")
    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody T entity){
        return ResponseEntity.ok(baseService.save(entity));
    }

    //@ApiOperation("根据ID删除信息")
    @PostMapping(value = "/deleteById")
    public ResponseEntity<?> deleteById(int id){
        baseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //@ApiOperation("根据ID数组删除信息")
    @PostMapping(value = "/deleteByIds")
    public ResponseEntity<?> deleteByIds(@RequestBody Integer[] ids){
        baseService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }

    //@ApiOperation("根据ID查找信息")
    @GetMapping(value = "/findById")
    public ResponseEntity<?> findById(int id){
        return ResponseEntity.ok(baseService.findById(id));
    }

    //@ApiOperation("根据条件分页查找信息")
    @GetMapping(value = "/findList")
    public ResponseEntity<?> findList(T entity, @PageableDefault(
            sort = "createTime", direction = Sort.Direction.DESC)Pageable pageable){
        return ResponseEntity.ok(baseService.findByExample(entity, pageable));
    }

}
