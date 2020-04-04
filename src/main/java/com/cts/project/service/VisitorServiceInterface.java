package com.cts.project.service;

import java.util.List;

import com.cts.project.bean.VisitorBean;
import com.cts.project.entity.VisitorEntity;

public interface VisitorServiceInterface {
	void saveVisitor(VisitorBean visitorBean);

	String validateVisitorLogin(String userName, String passWord);

	List<VisitorEntity> getVisitor(String userName);

	VisitorEntity getVisitorObject(String userName);
	VisitorEntity getVisitor(int visitorId);
	VisitorEntity updateVisitorObject(VisitorBean visitorBean);
	boolean changePassword(String userName,String passWord);
	int getId(String userName);

}
