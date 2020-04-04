package com.cts.project.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class EventVisitorMap implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sNo;
	
	private int visitorId;
	private int eventId;
	private int seats;
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public int getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	@Override
	public String toString() {
		return "EventVisitorMap [sNo=" + sNo + ", visitorId=" + visitorId + ", eventId=" + eventId + ", seats=" + seats
				+ "]";
	}
	public EventVisitorMap() {
		super();
	}
	public EventVisitorMap(int visitorId, int eventId, int seats) {
		super();
		this.visitorId = visitorId;
		this.eventId = eventId;
		this.seats = seats;
	}
	
	
}
