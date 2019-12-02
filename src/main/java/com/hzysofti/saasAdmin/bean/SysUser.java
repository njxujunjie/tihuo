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
@TableName("t_sys_user")
public class SysUser extends SuperEntity<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;

    /**
     * 名称
     */
    @TableId("user_name")
    private String userName;

    /**
     * 密码
     */
    @TableId("password")
    private String password;

    /**
     * 邮箱
     */
    @TableId("email")
    private String email;

    /**
     * 手机
     */
    @TableId("phone")
    private String phone;

    /**
     * 头像
     */
    @TableId("avatar")
    private String avatar;

    /**
     * 租户独立id
     * */
    @TableId("tenant_id")
    private Integer tenantId;

}
