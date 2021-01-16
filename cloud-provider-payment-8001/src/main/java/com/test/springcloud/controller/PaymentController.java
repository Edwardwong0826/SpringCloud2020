package com.test.springcloud.controller;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.PaymentSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentSerivce paymentSerivce;

    // the object send by consumer rest template is JSON, so need to add @RequestBody, else insert value is null
    @PostMapping(value="/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentSerivce.create(payment);
        log.info("****插入结果:" + result);

        if(result > 0){
            return  new CommonResult(200,"插入数据库成功,serverPort: ", 1);
        } else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
         Payment payment = paymentSerivce.getPaymentById(id);
        log.info("****查询结果:" + payment);

        if(payment != null){
            return  new CommonResult(200,"查询成功,serverPort: ", payment);
        } else {
            return new CommonResult(444,"没有对应记录，查询ID: " + id,null);
        }
    }

}
