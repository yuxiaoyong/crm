package com.xiaoyong.hrm.purchase.web;

import com.google.common.collect.Maps;
import com.xiaoyong.hrm.purchase.domain.StockDetail;
import com.xiaoyong.hrm.purchase.model.StockQuery;
import com.xiaoyong.hrm.support.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

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

    @GetMapping("/queryList")
    public ResponseEntity<?> findList(StockQuery query, Pageable pageable) {
        Map<String, Object> params = Maps.newHashMap();
        if(query.getStartTime() != null){
            params.put("gte(changeTime)", query.getStartTime());
        }
        if(query.getEndTime() != null){
            params.put("lte(changeTime)", query.getEndTime());
        }
        if(StringUtils.isNotBlank(query.getKeyword())){
            params.put("like(changeRemark)", "%" + query.getKeyword() + "%");
        }
        if(query.getProductId() != null){
            params.put("eq(product.id)", query.getProductId());
        }
        return ResponseEntity.ok(baseService.findByDslParamMap(params, pageable));
    }
}
