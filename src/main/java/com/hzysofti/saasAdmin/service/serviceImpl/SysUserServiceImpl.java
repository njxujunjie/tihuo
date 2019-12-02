package com.hzysofti.saasAdmin.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzysofti.saasAdmin.bean.SysUser;
import com.hzysofti.saasAdmin.mapper.SysUserMapper;
import com.hzysofti.saasAdmin.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {
}
