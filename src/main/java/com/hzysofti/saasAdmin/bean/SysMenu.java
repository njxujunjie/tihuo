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
@TableName("t_sys_menu")
public class SysMenu extends SuperEntity<SysMenu>{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("menu_id")
    private Integer menuId;

    /**
     * 菜单名称
     */
    @TableId("name")
    private String name;

    /**
     * 父菜单ID
     */
    @TableId("parent_id")
    private Integer parentId;

    /**
     * 图标
     */
    @TableId("icon")
    private String icon;

    /**
     * 排序
     */
    @TableId("sort")
    private Integer sort;

    /**
     * 菜单类型
     */
    @TableId("type")
    private Integer type;

    /**
     * 是否为外链（0是 1否）
     */
    @TableId("is_frame")
    private Integer isFrame;
}
