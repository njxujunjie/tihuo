package com.hzysofti.saasAdmin.controllor;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzysofti.saasAdmin.bean.SysUser;
import com.hzysofti.saasAdmin.bean.SysUserRole;
import com.hzysofti.saasAdmin.exception.BusinessException;
import com.hzysofti.saasAdmin.exception.CodeEnums;
import com.hzysofti.saasAdmin.service.SysUserRoleService;
import com.hzysofti.saasAdmin.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/saas/v1/sysUser")
@Api(value = "租户注册", tags = "租户注册")
public class SysUserControllor {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @GetMapping("/signIn")
    @ApiOperation(value = "注册", notes = "this is notes")
    @Transactional()
    public SysUser signIn(
            @ApiParam(name = "userName", value = "账号", required = true) @RequestParam(required = true) String userName,
            @ApiParam(name = "password", value = "密码", required = true) @RequestParam(required = true) String password,
            @ApiParam(name = "type", value = "类型", required = true) @RequestParam(required = true) Integer type
    ) {
        List<SysUser> list = sysUserService.list(new QueryWrapper<SysUser>().eq("user_name", userName));
        if (CollUtil.isNotEmpty(list)) {
            throw new BusinessException(CodeEnums.USER_ACCOUNT_EXIST.getCode(), "账号已存在，请直接登录");
        }
        //判断tenantId
        SysUser sysUser = null;
        SysUser user = sysUserService.getOne(new QueryWrapper<SysUser>().orderByDesc("id").last("LIMIT 1"));
        if (user != null) {
            sysUser = SysUser.builder().userName(userName).password(password).tenantId(user.getTenantId() + 1).build();
        } else {
            sysUser = SysUser.builder().userName(userName).password(password).tenantId(1).build();
        }
        Boolean flag = sysUserService.save(sysUser);
        if (!flag) {
            throw new BusinessException(CodeEnums.BUSINESS_ERR.getCode(), "注册失败");
        }
        //分配角色
        if(type==0){ //商家
            SysUserRole userRole = SysUserRole.builder().userId(sysUser.getId()).tenantId(sysUser.getTenantId()).roleId(14).build();
            sysUserRoleService.save(userRole);
        }else{
            SysUserRole userRole = SysUserRole.builder().userId(sysUser.getId()).tenantId(sysUser.getTenantId()).roleId(15).build();
            sysUserRoleService.save(userRole);
        }
        return sysUser;
    }

    @ApiOperation(value = "登录", notes = "this is notes")
    @GetMapping("/login")
    public SysUser login(
            @ApiParam(name = "userName", value = "账号", required = true) @RequestParam(required = true) String userName,
            @ApiParam(name = "password", value = "密码", required = true) @RequestParam(required = true) String password
    ) {
        SysUser sysUsers = sysUserService.getOne(new QueryWrapper<SysUser>().eq("user_name", userName));
        if (sysUsers == null) {
            throw new BusinessException(CodeEnums.USER_NOT_EXIST.getCode(), "此账号不存在");
        }
        if (!sysUsers.getPassword().equals(password)) {
            throw new BusinessException(CodeEnums.PWD_CHECK_ERR.getCode(), "密码输入错误");
        }
        return sysUsers;
    }
}
