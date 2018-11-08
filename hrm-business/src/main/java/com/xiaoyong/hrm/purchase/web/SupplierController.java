package com.xiaoyong.hrm.purchase.web;/**
 * Created by atlantisholic on 2018/9/15.
 */

import com.xiaoyong.hrm.purchase.domain.Supplier;
import com.xiaoyong.hrm.support.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SupplierController
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/15 19:51
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("/api/supplier")
public class SupplierController extends BaseController<Supplier>{
}
