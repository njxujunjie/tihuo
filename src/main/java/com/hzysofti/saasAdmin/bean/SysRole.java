package com.hzysofti.saasAdmin.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_sys_role")
public class SysRole extends SuperEntity<SysRole>{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("role_id")
    private Integer menuId;

    /**
     * 角色名称
     */
    @TableId("role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @TableId("role_desc")
    private String roleDesc;

    /**
     * 数据权限类型
     */
    @TableId("ds_type")
    private Integer dsType;

}
