package com.test.springcloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.test.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") //那些  @HystrixCommand fallbackMethod没有指明的，全部运行这个global方法
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") int id)
    {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3500") //this send limit time that can wait
    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") int id)
    {
        // 调用的时候如果provider服务 被占满或者high concurrent会会导致consumer 也要等待被拖死， 有可能会引发联级故障， 造成其他call consumer的多个微服务一起故障
        // 或者调用到down的服务. 超时， 自己出故障或者有自我要求， 等等类似,， consumer 不能一直等待， 必须有服务降级， fallback 是提供一个兜底的方法
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout2/{id}")
    public String paymentInfo_TimeOut2(@PathVariable("id") int id)
    {
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    //fallback method signature/parameter need to be same with @HystrixCommand method one
    public String paymentInfo_TimeOutHandler(@PathVariable("id") int id)
    {
        return "This is consumer80, id:" + id +" called payment system is busy, please wait retry in 10seconds  or inspect own";
    }

    // 下面是global fallback method, and cannot have parameter, else will error
    public String payment_Global_FallbackMethod()
    {
        return "Global exception message, please try later!";
    }

}
