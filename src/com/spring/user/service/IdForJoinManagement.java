package com.spring.user.service;

import java.util.List;

public interface IdForJoinManagement {
	
	public boolean isEmpty(List<String> list);
	public void isExist(String inputId);
	//return left time
	public String lock(String inputId); 
	
	public void release(String inputId); 
	
}
