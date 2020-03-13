package com.dzb.springcloud.dao;

import com.dzb.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ding ZhenBei
 * @data 2020/3/10 20:19
 */
@Mapper
public interface PaymentMapper {

    int create(Payment payment);

    Payment getPaymentById(long id);
}
