package com.dzb.springcloud.controller;

import com.dzb.springcloud.annotation.SysoLog;
import com.dzb.springcloud.entities.CommonResult;
import com.dzb.springcloud.entities.Payment;
import com.dzb.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ding ZhenBei
 * @data 2020/3/11 22:40
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @SysoLog("创建支付流水")
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        long result = paymentService.create(payment);
        return result > 0? new CommonResult(200,"插入数据库成功,serverPort:" + serverPort,result):new CommonResult(444,"插入数据库失败,serverPort:" + serverPort,null);
    }
    @SysoLog("获取支付流水")
    @GetMapping("/payment/get/{id}")
    public CommonResult create(@PathVariable("id") long id){
        Payment payment = paymentService.getPaymentById(id);
        return payment != null? new CommonResult(200,"查询成功,serverPort:" + serverPort,payment):new CommonResult(444,"没有对应记录:" + id + ",serverPort:" + serverPort,null);
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("===========>>>>>>>>>>service:[{}]", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("=======================================================");
            log.info("InstanceId:[{}]", instance.getInstanceId());
            log.info("ServiceId:[{}]", instance.getServiceId());
            log.info("Host:[{}]", instance.getHost());
            log.info("getPort:[{}]", instance.getPort());
            log.info("Uri:[{}]", instance.getUri());
            log.info("Metadata:[{}]", instance.getMetadata());
            log.info("=======================================================");
        }
        return this.discoveryClient;
    }

}
