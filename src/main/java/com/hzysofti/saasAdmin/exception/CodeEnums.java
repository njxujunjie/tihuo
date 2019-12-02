package com.hzysofti.saasAdmin.exception;
/**
 * @author xujunjie
 * @version 创建时间：2018年6月22日 上午9:32:57
 * 类说明
 */
public enum CodeEnums {
	//异常枚举名称格式：   模块名    类型/操作     异常原因       
		//示例：菜单修改失败        MENU_UPDATE_ERR
		//示例：菜单名称参数不可为空   MENU_NAME_NOT_FOUND_ERR
		
		//异常代码定义：异常代码4位数字       前两位模块代码     后两位 按顺序定义
	   
	     //用户信息  10、
	    //排位机    11
	
	/**
	 * 用户名不正确 状态码 10
	 */
	USER_LOGIN_ERR(1001, "用户名不正确"),
	USER_NOT_FOUND_ERR(1102, "用户未登录，获取不到用户信息"),
	PWD_CHECK_ERR(1102, "密码输入错误"),
	USER_NOT_EXIST(1003, "用户不存在"),
	CODE_CHECK_ERR(1101, "code不存在或已过期"),
	TOKEN_CHECK_EXP_ERR(1102, "token已过期"),
	TOKEN_CHECK_NOT_FOUND(1103, "token不存在"),
	TOKEN_CHECK_PATTEN_ERR(1104, "token不正确 无法解析"),
	TOKEN_SIGN_ERR(1105,"token签名异常"),
	TOKEN_ILLEGAL_ERR(1106,"token不合法"),
	USER_ACCOUNT_EXIST(1107,"账号已存在"),
	
	/**
	 * 公共模块 状态码  20
	 */
	ENTITY_ID_NOT_EXIST(2001,"找不到原数据 请检查数据是否被删除或核对version信息"),
	ENTITY_SAVE_ERROR(2002,"数据保存失败"),
	ARGS_NOT_RIGHT(2003,"参数异常"),
	FILE_NOT_FOUND(2004,"文件不存在"), 
	BUSINESS_ERR(2005,"业务异常"),
	
	
	
	/**
	 * 模块 状态码  30
	 */
	MODULE_RECOMMEND_MORE_THEN_EIGHT(3001,"推荐首页数量已超过8个"),


	/** SysSetting 系统设置 */
	SYS_SETTING_ADD_ERR(3001,"添加数据失败"),
	SYS_SETTING_EDIT_ERR(3002,"编辑数据失败"),
	SYS_SETTING_DELETE_ERR(3003,"数据已删除"),
	SYS_SETTING_EXIST_ERR(3004,"数据已存在"),
	SYS_SETTING_ITEM_PARAM_ERE(5001,"批量编辑系统设置参数错误"),
	;


	
	private Integer code;
	private String msg;
	
	CodeEnums(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}