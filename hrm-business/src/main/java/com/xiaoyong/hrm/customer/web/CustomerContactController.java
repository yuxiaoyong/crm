package com.xiaoyong.hrm.customer.web;/**
 * Created by atlantisholic on 2018/8/19.
 */

import com.xiaoyong.common.util.ExcelUtils;
import com.xiaoyong.hrm.customer.domain.Customer;
import com.xiaoyong.hrm.customer.domain.CustomerContact;
import com.xiaoyong.hrm.customer.domain.CustomerProduct;
import com.xiaoyong.hrm.customer.model.CustomerContactQuery;
import com.xiaoyong.hrm.customer.service.CustomerContactService;
import com.xiaoyong.hrm.product.domain.Product;
import com.xiaoyong.hrm.product.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomerContactController
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/19 17:30
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("/api/contact")
public class CustomerContactController {

    @Autowired
    CustomerContactService customerContactService;
    @Autowired
    ProductService productService;

    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody CustomerContact contact){
        return ResponseEntity.ok(customerContactService.save(contact));
    }

    @PostMapping(value = "/deleteById")
    public ResponseEntity<?> deleteById(int id){
        customerContactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/deleteByIds")
    public ResponseEntity<?> deleteByIds(@RequestBody Integer[] ids){
        customerContactService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<?> findById(int id){
        return ResponseEntity.ok(customerContactService.findById(id));
    }

    @GetMapping(value = "/findList")
    public ResponseEntity<?> findList(CustomerContactQuery query, @PageableDefault(
            sort = "createTime", direction = Sort.Direction.DESC)Pageable pageable){
        return ResponseEntity.ok(customerContactService.findByQuery(query, pageable));
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public ResponseEntity<?> importCustomer(@RequestParam("file") MultipartFile file) throws Exception{
        List<Map<String, String>> records = ExcelUtils.readExcelData(file.getInputStream());
        List<Product> productList = productService.findByExample(new Product());
        for(Map<String, String> record: records){
            System.out.println(record);
            CustomerContact contact = buildContact(record, productList);
            if(contact != null){
                customerContactService.save(contact);
            }
        }
        return ResponseEntity.noContent().build();
    }

    private CustomerContact buildContact(Map<String, String> record, List<Product> productList) throws Exception{
        if(StringUtils.isBlank(record.get("廠商名稱"))){
            return null;
        }
        Customer customer = new Customer();
        CustomerContact contact = new CustomerContact();
        contact.setCustomer(customer);

        customer.setName(record.get("廠商名稱"));
        customer.setAddress(record.get("地  址"));
        customer.setTrade(record.get("廠商別"));
        customer.setGainTime(DateUtils.parseDate(record.get("初交易日"), new String[]{"yyyy-MM-dd", "yyyy.MM.dd"}));
        customer.setStatus("已成交客户");
        customer.setRank(5);
        customer.setProducts(getProducts(record.get("產品別"), productList));
        String telephone = record.get("電  話");
        if(StringUtils.contains(telephone, "-")){
            contact.setTelephone(telephone);
        }else{
            contact.setMobile(telephone);
        }
        contact.setFax(record.get("傳真"));
        contact.setRealname(record.get("聯絡人"));
        contact.setEmail(record.get("e-mail"));
        contact.setGender("男");
        return contact;
    }

    private List<CustomerProduct> getProducts(String value, List<Product> productList){
        if(StringUtils.isBlank(value)){
            return new ArrayList<>(0);
        }
        String[] productTypes = StringUtils.split(value,"/,");
        List<CustomerProduct> customerProducts = new ArrayList<>();
        for(String productType: productTypes){
            CustomerProduct customerProduct = getProduct(productType, productList);
            if(customerProduct != null) {
                customerProducts.add(customerProduct);
            }
        }
        return customerProducts;
    }

    private CustomerProduct getProduct(String productType, List<Product> productList){
        if(StringUtils.isBlank(productType)){
            return null;
        }
        CustomerProduct customerProduct = null;
        for(Product product: productList){
            if(StringUtils.contains(productType.toLowerCase(), product.getType().toLowerCase())){
                customerProduct = new CustomerProduct();
                customerProduct.setProductId(product.getId());
                customerProduct.setNumber(0);
                break;
            }
        }
        return customerProduct;
    }
}
