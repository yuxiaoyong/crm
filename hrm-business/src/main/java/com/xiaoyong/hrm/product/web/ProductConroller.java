package com.xiaoyong.hrm.product.web;/**
 * Created by atlantisholic on 2018/8/27.
 */

import com.xiaoyong.hrm.product.domain.Product;
import com.xiaoyong.hrm.product.service.ProductService;
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
 * @ClassName ProductConroller
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/27 23:24
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("/api/product")
public class ProductConroller {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @PostMapping(value = "/deleteById")
    public ResponseEntity<?> deleteById(int id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/deleteByIds")
    public ResponseEntity<?> deleteByIds(@RequestBody Integer[] ids){
        productService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<?> findById(int id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping(value = "/findList")
    public ResponseEntity<?> findList(Product product, @PageableDefault(
            sort = "createTime", direction = Sort.Direction.DESC)Pageable pageable){
        return ResponseEntity.ok(productService.findByExample(product, pageable));
    }

}
