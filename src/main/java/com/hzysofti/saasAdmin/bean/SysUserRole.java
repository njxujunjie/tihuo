package com.hzysofti.saasAdmin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("t_sys_user_role")
public class SysUserRole extends SuperEntity<SysUserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;

    /**
     * 用户ID
     */
    @TableId("user_id")
    private Integer userId;

    /**
     * 角色ID
     */
    @TableId("role_id")
    private Integer roleId;

    /**
     * 租户独立id
     * */
    @TableId("tenant_id")
    private Integer tenantId;
}
