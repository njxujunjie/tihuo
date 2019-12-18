package com.michael.saas.tenant.common.queue;

import com.alibaba.fastjson.JSON;
import com.michael.saas.tenant.config.TenantDataSourceProvider;
import com.michael.saas.tenant.domain.Tenant;
import com.michael.saas.tenant.service.TenantService;
import com.michael.saas.tenant.util.DBUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.concurrent.CountDownLatch;

public class Receiver {
    private static final Logger logger = Logger.getLogger(Receiver.class);

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    @Autowired
    private TenantService tenantService;

    @Autowired
    private TenantDataSourceProvider tenantDataSourceProvider;
    /**
     * 消息处理
     * @param message
     */
    @Transactional(rollbackOn = Exception.class)
    public void objectMessage(String message) {
        QueueTemplate queueTemplate = JSON.parseObject(message,QueueTemplate.class);
        if ("register".equals(queueTemplate.getMethod())){
            Tenant tenant = JSON.parseObject(JSON.toJSONString(queueTemplate.getObject()),Tenant.class);
            try {
                DBUtils.createDataBase(tenant.getUrl(),tenant.getDatabase(),tenant.getUsername(),tenant.getPassword());
                tenantDataSourceProvider.addDataSource(tenant);
                tenantService.save(tenant);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
    }


}