package com.test.springcloud.service;

import com.test.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentSerivce {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
