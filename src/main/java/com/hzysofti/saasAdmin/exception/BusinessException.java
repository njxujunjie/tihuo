package com.hzysofti.saasAdmin.exception;


import lombok.Data;

/**
 * 类说明:业务异常类
 */
@Data
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer code;
	
	private boolean showLog = true;
	
	private String msg;
	
	private Object  body = new Object();
	
	
	public BusinessException(Integer status,String msg){
		super(msg);
		this.code = status;
		this.msg = msg;
	}
	
	public BusinessException(Integer status,String msg,boolean isShowLog){
		super(msg);
		this.code = status;
		this.msg = msg;
		this.showLog = isShowLog;
	}
	
	public BusinessException(Integer status,String msg,Object body){
		super(msg);
		this.code = status;
		this.msg = msg;
		if(body!=null){
			this.body = body;
		}
	}
	public BusinessException(Integer status,String msg,Object body,boolean isShowLog){
		super(msg);
		this.code = status;
		this.msg = msg;
		if(body!=null){
			this.body = body;
		}
		this.showLog = isShowLog;
	}
	
	public BusinessException(CodeEnums codeEnums,Object body) {
		 super(codeEnums.getMsg());
		 this.code = codeEnums.getCode();
		 this.msg = codeEnums.getMsg();
		 if(body!=null){
			this.body = body;
		 }
	 }
	 public BusinessException(CodeEnums codeEnums) {
		 super(codeEnums.getMsg());
		 this.code = codeEnums.getCode();
		 this.msg = codeEnums.getMsg();
	 }
}
