package com.cts.project.dao;

import java.util.List;

import com.cts.project.bean.VisitorBean;
import com.cts.project.entity.VisitorEntity;

public interface DAO {

	// void update(Object object);
	void saveVisitor(VisitorBean visitorBean);

	String getPassword(String userName);
	
	List<VisitorEntity> getVisitor(String userName);
	VisitorEntity updateVisitorObject(VisitorBean visitorBean);
	boolean changePassword(String userName,String passWord);
	VisitorEntity getVisitor(int visitorId);
	int getId(String userName);

}
