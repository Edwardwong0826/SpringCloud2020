package com.test.springcloud.controller;


import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment)
    {
        // rest template will wrap the payment into JSON body and sent to provider payment 8001 url,
        // so when in payment 8001 need to add @RequestBody
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment ,CommonResult.class );
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
}