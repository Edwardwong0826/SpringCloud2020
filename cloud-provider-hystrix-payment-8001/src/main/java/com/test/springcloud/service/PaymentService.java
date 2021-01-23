package com.test.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * normal access, sure OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(int id)
    {
        return "thread pool:  " + Thread.currentThread().getName() + "  paymentInfo_OK,id:  " + id+"\t"+"haha";
    }

    // this 开启降级服务功能 to let microservice return 一个保底方法/friendly message instead of error
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") //this send limit time that can wait
    })
    public String paymentInfo_TimeOut(int id)
    {
        int timeNumber = 5;
        // int age = 10/0;
        try
        {
            TimeUnit.SECONDS.sleep(timeNumber);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return "thread pool:  " + Thread.currentThread().getName() + "  paymentInfo_Timeout,id:  " + id+"\t"+"haha" + "耗时:(秒) " +timeNumber;
    }

    public String paymentInfo_TimeOutHandler(int id)
    {
        return "thread pool:  " + Thread.currentThread().getName() + "  server busy, please try later,id:  " + id+"\t"+"oh no";
    }

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),              //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),    //请求数达到后才计算
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),  //错误率达到多少跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") int id)
    {

        if(id < 0){
            throw  new RuntimeException("****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return  Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") int id){
        return "id 不能为负数,请稍后再试， o(╥﹏╥)o id: " + id;
    }

}
