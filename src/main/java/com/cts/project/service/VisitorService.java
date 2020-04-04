package com.cts.project.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.project.bean.VisitorBean;
import com.cts.project.dao.DAO;
import com.cts.project.entity.VisitorEntity;
@Service
public class VisitorService implements VisitorServiceInterface{
	@Autowired
	private DAO dao;
	
	@Override
	@Transactional
	public void saveVisitor(VisitorBean visitorBean) {
		
		dao.saveVisitor(visitorBean);
	
	}

	@Override
	public String validateVisitorLogin(String userName, String passWord) {
		String passCode = dao.getPassword(userName);
		System.out.println(passCode);
		if(passCode.equals("error"))
		{
			return "notFound";
		}
		else
		{
			if(passCode.equals(passWord))
			{
				return "found";
			}
			else
			{
				return "incorrect";
			}
		}
		
	}

	@Override
	@Transactional
	public List<VisitorEntity> getVisitor(String userName) {
		return dao.getVisitor(userName);
	}

	@Override
	public VisitorEntity getVisitorObject(String userName) {
		List<VisitorEntity> list = dao.getVisitor(userName);
		if(list.isEmpty())
		{
			return null;
		}
		else
			return list.get(0);
	}

	@Override
	@Transactional
	public VisitorEntity updateVisitorObject(VisitorBean visitorBean) {
		
		return dao.updateVisitorObject(visitorBean);
	}

	@Override
	@Transactional
	public boolean changePassword(String userName, String passWord) {
		
		return dao.changePassword(userName, passWord);
	}

	@Override
	public VisitorEntity getVisitor(int visitorId) {
		// TODO Auto-generated method stub
		return dao.getVisitor(visitorId);
	}

	@Override
	public int getId(String userName) {
		// TODO Auto-generated method stub
		return dao.getId(userName);
	}

}
