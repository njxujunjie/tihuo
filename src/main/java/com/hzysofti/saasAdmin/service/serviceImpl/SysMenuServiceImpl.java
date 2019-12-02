package com.hzysofti.saasAdmin.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzysofti.saasAdmin.bean.SysMenu;
import com.hzysofti.saasAdmin.mapper.SysMenuMapper;
import com.hzysofti.saasAdmin.service.SysMenuService;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
}
