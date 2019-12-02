package com.hzysofti.saasAdmin.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "hzysofti.tenant")
public class PreTenantConfigProperties {

    /**
     * 维护租户id
     */
    private String tenantIdColumn;

    /**
     * 多租户的数据表集合
     */
    private List<String> ignoreTenantTables = new ArrayList<>();

}
