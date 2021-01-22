package com.test.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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

}
