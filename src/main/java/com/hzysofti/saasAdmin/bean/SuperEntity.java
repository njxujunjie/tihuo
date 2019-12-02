package com.hzysofti.saasAdmin.bean;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;



/**
* 类说明  公共实体类
* @param <T>
 */
@Data
public class SuperEntity<T extends Model<T>> extends Model<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@JsonFormat(pattern = "MM-dd HH:mm")
	private Date createTime;

    /**
     * 更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @TableField("update_time")
    private Date updateTime;

	/**
	 * 1删除 0未删除
	 */
	@TableField("is_del")
	@TableLogic
    @JsonIgnore
	private Integer isDel;
	
	@Override
	protected Serializable pkVal() {
		return null;
	}
	
}
