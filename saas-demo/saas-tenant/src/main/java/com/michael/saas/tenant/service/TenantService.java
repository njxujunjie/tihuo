package com.michael.saas.tenant.service;



import com.michael.saas.tenant.dao.TenantDao;
import com.michael.saas.tenant.domain.Tenant;
import com.michael.saas.tenant.util.GUIDHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {

    @Autowired
    private TenantDao tenantDao;

    public Tenant findById(Integer id){
        return tenantDao.findById(id);
    };

    public List<Tenant> selectAll(){
        return tenantDao.findAll();
    };

    public Tenant findByAccountAndToken(String accoun, String token){
        return tenantDao.findByAccountAndToken(accoun, token);
    };

    public Tenant findByUsernameAndPasswordAndAccount(String account,String username, String password){
        return tenantDao.findByUsernameAndPasswordAndAccount(account,username,password);
    };

    public Tenant save(Tenant tenant) throws Exception{
        if (StringUtils.isBlank(tenant.getId().toString())){
            tenant.setId(Integer.parseInt(GUIDHelper.genRandomGUID()));
        }
        return tenantDao.save(tenant);
    }

}
