package com.cts.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.project.dao.EventDao;
import com.cts.project.entity.EventEntity;
@Service
public class EventServiceImplementation implements EventService{
	@Autowired
	private EventDao dao;
	
	@Override
	@Transactional
	public void saveEvent(EventEntity eventEntity) {

		dao.saveEvent(eventEntity);
	}

	@Override
	@Transactional
	public List<EventEntity> showEvent() {
		List list = dao.showEvent();
		if(list.isEmpty())
		{
			return null;
		}
		return list;
	}

	@Override
	@Transactional
	public EventEntity showEvent(int eventId) {
		EventEntity theEvent = dao.showEvent(eventId);
		return theEvent;
	}

	@Override
	@Transactional
	public boolean registeredToevent(int eventId, int visitorId, int seats) {
		return dao.registeredToevent(eventId, visitorId, seats);
	}

	@Override
	@Transactional
	public List<EventEntity> getRegisteredEvent(int visitorId) {

		return dao.getRegisteredEvent(visitorId);
	}

	@Override
	@Transactional
	public boolean isAlreadyRegistered(int visitorId, int eventId) {
		
		return dao.isAlreadyRegistered(visitorId, eventId);
	}

	@Override
	public boolean cancelEventTicket(int visitorId, int eventId) {
		// TODO Auto-generated method stub
		return dao.cancelEventTicket(visitorId, eventId);
	}

}
