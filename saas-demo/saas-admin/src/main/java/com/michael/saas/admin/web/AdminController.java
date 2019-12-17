package com.michael.saas.admin.web;

import com.michael.saas.admin.common.queue.QueueService;
import com.michael.saas.admin.domain.Tenant;
import com.michael.saas.admin.service.TenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
@Api(value = "/admin",tags = "租户管理")
public class AdminController {

    @Autowired
    private QueueService queueService;

    @Autowired
    private TenantService tenantService;

    @PostMapping(value = "/register")
    @ApiOperation(value = "注册",notes = "this is notes")
    public boolean register(Tenant tenant){
        try {
            tenant.setUrl("jdbc:mysql://127.0.0.1:3306/");
            tenant.setDatabase("saas_tenant_" + tenant.getAccount());
            tenant.setUsername("root");
            tenant.setPassword("123456");
            tenant = tenantService.save(tenant);
            queueService.send("register",tenant);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
