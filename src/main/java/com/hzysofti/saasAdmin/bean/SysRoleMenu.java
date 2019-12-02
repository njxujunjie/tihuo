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
@TableName("t_sys_role_menu")
public class SysRoleMenu extends SuperEntity<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Integer id;

    /**
     * 角色ID
     */
    @TableId("role_id")
    private Integer roleId;

    /**
     * 菜单ID
     */
    @TableId("menu_id")
    private Integer menuId;
}
