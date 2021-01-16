package com.test.springcloud.service.Impl;

import com.test.springcloud.dao.PaymentDao;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.PaymentSerivce;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;


@Service
public class PaymentServiceImpl implements PaymentSerivce
{

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment)
    {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id)
    {
        Payment  p = paymentDao.getPaymentById(id);
        return p;
    }
}
