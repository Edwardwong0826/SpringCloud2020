package com.test.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_OK(int id) {
        return "----- PaymentFallbackService fall back paymentInfo_OK!---------";
    }

    @Override
    public String paymentInfo_TimeOut(int id) {
        return "----- PaymentFallbackService fall back paymentInfo_TimeOut!---------";
    }
}
