package com.dzb.springcloud.service;

import com.dzb.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author Ding ZhenBei
 * @data 2020/3/11 22:41
 */

public interface PaymentService {
    long create(Payment payment);

    Payment getPaymentById(@Param("id")long id);
}
