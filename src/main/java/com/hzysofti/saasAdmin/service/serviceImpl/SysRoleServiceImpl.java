package com.hzysofti.saasAdmin.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzysofti.saasAdmin.bean.SysRole;
import com.hzysofti.saasAdmin.mapper.SysRoleMapper;
import com.hzysofti.saasAdmin.service.SysRoleService;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
