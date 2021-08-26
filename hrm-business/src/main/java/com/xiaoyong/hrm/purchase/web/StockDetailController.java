package com.xiaoyong.hrm.purchase.web;

import com.xiaoyong.hrm.purchase.domain.StockDetail;
import com.xiaoyong.hrm.support.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName StockDetailController
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/26 22:43
 * @Version V1.0
 **/
@Controller
@RequestMapping("/api/stock/detail")
public class StockDetailController extends BaseController<StockDetail> {
}
