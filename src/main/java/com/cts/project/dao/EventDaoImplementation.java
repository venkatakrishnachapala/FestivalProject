package com.cts.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.project.entity.EventEntity;
import com.cts.project.entity.EventVisitorMap;
@Repository
public class EventDaoImplementation implements EventDao{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public void saveEvent(EventEntity eventEntity) {
		Session session = sf.openSession();
		session.saveOrUpdate(eventEntity);
		
	}

	@Override
	public List<EventEntity> showEvent() {
		Session session = sf.openSession();
		Query theQuery = session.createQuery("from EventEntity order by eventId");
		List<EventEntity> list = theQuery.list(); 
		return list;
	}

	@Override
	public EventEntity showEvent(int eventId) {
		Session session = sf.openSession();
		EventEntity theEvent = (EventEntity)session.get(EventEntity.class, eventId);
		return theEvent;
	}

	@Override
	public boolean registeredToevent(int eventId, int visitorId, int seats) {
		Session session1 = sf.openSession();
		EventVisitorMap eVm = new EventVisitorMap();
		eVm.setVisitorId(visitorId);
		eVm.setEventId(eventId);
		eVm.setSeats(seats);
		System.out.println("in Session1");
		session1.saveOrUpdate(eVm);
		session1.close();
		
		
		Session session2 = sf.openSession();
		EventEntity theEvent = (EventEntity)session2.get(EventEntity.class, eventId);
		int seatsBefore = theEvent.getSeatsAvailable();
		int seatsAfter=seatsBefore-seats;
		Query theQuery = session2.createQuery("update EventEntity e set e.seatsAvailable=?"+" where e.eventId=?");
		theQuery.setParameter(0,seatsAfter);
		theQuery.setParameter(1, eventId);
		int num = theQuery.executeUpdate();
		if(num<=0)
		{
			return false;
		}
		return true;
	}

	@Override
	public List<EventEntity> getRegisteredEvent(int visitorId) {
		Session session = sf.openSession();
		Query theQuery = session.createQuery("select eventId from EventVisitorMap where visitorId=:visitorId");
		theQuery.setInteger("visitorId", visitorId);
		List<Integer> regEventId = theQuery.list();
		session.close();
		Session session2 = sf.openSession();
		List<EventEntity> regEvent = new ArrayList<>();
		for(Integer i:regEventId)
		{
			EventEntity event = (EventEntity)session2.get(EventEntity.class, i);
			regEvent.add(event);
		}
		return regEvent;
	}

	@Override
	public boolean isAlreadyRegistered(int visitorId, int eventId) {
		Session session = sf.openSession();
		Query theQuery = session.createQuery("select eventId from EventVisitorMap where visitorId=:visitorId");
		theQuery.setInteger("visitorId", visitorId);
		List<Integer> list = theQuery.list();
		if(list.contains(eventId))
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean cancelEventTicket(int visitorId, int eventId) {
		Session session2 = sf.openSession();
		Query theQuery2 = session2.createQuery("from EventVisitorMap where visitorId=:visitorId and eventId= :eventId");
		theQuery2.setInteger("visitorId", visitorId);
		theQuery2.setInteger("eventId",eventId);
		List<EventVisitorMap> list = theQuery2.list();
		session2.flush();
		session2.close();
		
		System.out.println(list);
		System.out.println("-------\n\n\n\n\n\n\n----------------------------------");
		Session session1 = sf.openSession();
		Query theQuery1 = session1.createQuery("delete from EventVisitorMap where visitorId = :visitorId and eventId= :eventId");
		theQuery1.setInteger("visitorId", visitorId);
		theQuery1.setInteger("eventId", eventId);
		System.out.println("-----delete row--\n\n\n\n\n\n\n----------------------------------");

		int row = theQuery1.executeUpdate();
		session1.flush();
		session1.close();
		System.out.println("------"+row+"-----------");
		
		Session session3 = sf.openSession();
		EventEntity theEvent = (EventEntity)session3.get(EventEntity.class, eventId);
		int seatsBefore = theEvent.getSeatsAvailable();
		int seatsAfter=seatsBefore+list.get(0).getSeats();
		Query theQuery3 = session3.createQuery("update EventEntity e set e.seatsAvailable=?"+" where e.eventId=?");
		theQuery3.setParameter(0,seatsAfter);
		theQuery3.setParameter(1, eventId);
		int num = theQuery3.executeUpdate();
		session3.flush();
		session3.close();
		System.out.println(num);
		if(row<=0)
		{
			return false;
		}
		return true;
	}

}
