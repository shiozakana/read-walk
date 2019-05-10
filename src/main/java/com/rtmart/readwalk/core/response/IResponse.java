package com.rtmart.readwalk.core.response;

public interface IResponse<T> {
	
	String getCode();
	
	String getMessage();
	
	Object getBody();
}
