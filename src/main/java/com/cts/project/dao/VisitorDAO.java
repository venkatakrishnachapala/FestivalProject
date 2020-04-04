package com.cts.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.project.bean.VisitorBean;
import com.cts.project.entity.VisitorEntity;

@Repository
public class VisitorDAO implements DAO {
	/*
	 * @Autowired private VisitorEntity entity;
	 */
	@Autowired
	private DAO dao;
	@Autowired
	private SessionFactory sf;

	@Override
	public void saveVisitor(VisitorBean visitorBean) {
		Session session = sf.openSession();
		VisitorEntity entity = new VisitorEntity();
		entity.setFirstName(visitorBean.getFirstName());
		entity.setLastName(visitorBean.getLastName());
		entity.setUserName(visitorBean.getUserName());
		entity.setPassWord(visitorBean.getPassWord());
		entity.setEmail(visitorBean.getEmail());
		entity.setPhoneNumber(visitorBean.getPhoneNumber());
		entity.setAddress(visitorBean.getAddress());
		session.saveOrUpdate(entity);
	}

	@Override
	public String getPassword(String userName) {
		String passCode = null;
		List<VisitorEntity> pass = dao.getVisitor(userName);
		System.out.println(pass);
		if (pass.isEmpty()) {
			passCode = "error";
		} else {
			for (VisitorEntity vEn : pass) {
				passCode = vEn.getPassWord();
			}
		}
		return passCode;
	}

	@Override
	public List<VisitorEntity> getVisitor(String userName) {
		Session session = sf.openSession();
		Query query = session.createQuery("from VisitorEntity where userName =:userName");
		query.setString("userName", userName);
		// query.executeUpdate();
		List<VisitorEntity> results = query.list();
		if (results == null) {
			return null;
		} else
			return results;
	}

	@Override
	public VisitorEntity updateVisitorObject(VisitorBean visitorBean) {
		Session session = sf.openSession();
		VisitorEntity entity = (VisitorEntity)session.load(VisitorEntity.class, dao.getId(visitorBean.getUserName()));
		entity.setFirstName(visitorBean.getFirstName());
		entity.setLastName(visitorBean.getLastName());
		entity.setUserName(visitorBean.getUserName());
		entity.setPassWord(visitorBean.getPassWord());
		entity.setEmail(visitorBean.getEmail());
		entity.setPhoneNumber(visitorBean.getPhoneNumber());
		entity.setAddress(visitorBean.getAddress());
		System.out.println(entity);
		System.out.println(visitorBean);
		session.flush();
		System.out.println("after flush"+entity);
		return entity;
	}

	@Override
	public boolean changePassword(String userName, String passWord) {
		Session session = sf.openSession();
		boolean result=false;
		Query theQuery = session.createQuery("update VisitorEntity v set v.passWord=?"+" where v.userName=?");
		theQuery.setParameter(0, passWord);
		theQuery.setParameter(1, userName);
		int num = theQuery.executeUpdate();
		session.close();
		if(num>0)
		{
			result=true;
		}
		return result;
	}

	@Override
	public VisitorEntity getVisitor(int visitorId) {
		Session session = sf.openSession();
		VisitorEntity visitor = (VisitorEntity)session.get(VisitorEntity.class, visitorId);
		return visitor;
	}

	@Override
	public int getId(String userName) {
		Session session = sf.openSession();
		Query theQuery = session.createQuery("select id from VisitorEntity where userName=:userName");
		theQuery.setString("userName", userName);
		List<Integer> list = theQuery.list();
		
		return list.get(0);
	}

}
