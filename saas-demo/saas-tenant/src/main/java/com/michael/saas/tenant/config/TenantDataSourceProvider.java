package com.michael.saas.tenant.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.michael.saas.tenant.domain.Tenant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个类负责根据租户ID来提供对应的数据源
 * @author lanyuanxiaoyao
 * @version 1.0
 */
@Component
public class TenantDataSourceProvider {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    // 使用一个map来存储我们租户和对应的数据源，租户和数据源的信息就是从我们的tenant_info表中读出来
    private static Map<String, DataSource> dataSourceMap = new HashMap<>();

    /**
     * 静态建立一个数据源，也就是我们的默认数据源，假如我们的访问信息里面没有指定tenantId，就使用默认数据源。
     * 在我这里默认数据源是cloud_config，实际上你可以指向你们的公共信息的库，或者拦截这个操作返回错误。
     */

//    @PostConstruct
//    private void init(){
            static {
        DruidDataSource dataSourceBuilder = new DruidDataSource();
        //DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.setUrl("jdbc:mysql://localhost:3306/saas_tenant?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false");
        dataSourceBuilder.setUsername("root");
        dataSourceBuilder.setPassword("123456");
        dataSourceBuilder.setDriverClassName("com.mysql.jdbc.Driver");
        dataSourceMap.put("Default", dataSourceBuilder);
    }

    // 根据传进来的tenantId决定返回的数据源
    public static DataSource getTenantDataSource(String tenantId) {
        if (dataSourceMap.containsKey(tenantId)) {
            return dataSourceMap.get(tenantId);
        } else {
            return dataSourceMap.get("Default");
        }
    }

    // 初始化的时候用于添加数据源的方法
    public void addDataSource(Tenant tenantInfo) {
        if(!dataSourceMap.containsKey(tenantInfo.getId())){
            DruidDataSource dataSource = new DruidDataSource();
            //DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            String url = tenantInfo.getUrl().concat(tenantInfo.getDatabase()).concat("?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false");
            dataSource.setUrl(url);
            dataSource.setUsername(tenantInfo.getUsername());
            dataSource.setPassword(tenantInfo.getPassword());
            dataSource.setDriverClassName(driverClassName);
            dataSourceMap.put(tenantInfo.getId().toString(), dataSource);
        }
    }

}
