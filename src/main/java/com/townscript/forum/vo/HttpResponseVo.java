package com.townscript.forum.vo;

public class HttpResponseVo {
	//for http response
	
	private Long id;
	private Object data;
	private String errorDesc;
	private String errorCode;
	
	public HttpResponseVo(String errorCode, String errorDesc, Object data, Long id ){
		this.errorCode = errorCode;
		this.errorDesc = errorCode;
		this.data = data;
		this.id = id;
	}
}
