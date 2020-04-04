package com.cts.project.bean;

import java.io.Serializable;

public class VisitorLoginBean implements Serializable  {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String passWord;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public VisitorLoginBean(String userName, String passWord) {
		
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public VisitorLoginBean() {
		
	}
	@Override
	public String toString() {
		return "VisitorLoginBean [userName=" + userName + ", passWord=" + passWord + "]";
	}
	
}
