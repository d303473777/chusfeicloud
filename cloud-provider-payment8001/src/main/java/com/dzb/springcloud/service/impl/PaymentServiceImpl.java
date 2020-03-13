package com.dzb.springcloud.service.impl;

import com.dzb.springcloud.dao.PaymentMapper;
import com.dzb.springcloud.entities.Payment;
import com.dzb.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ding ZhenBei
 * @data 2020/3/11 22:41
 */
@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public long create(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment getPaymentById(long id) {
        return paymentMapper.getPaymentById(id);
    }
}
