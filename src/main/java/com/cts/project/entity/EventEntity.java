package com.cts.project.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class EventEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eventId;
	private String eventName;
	private String description;
	private int duration;
	private String eventType;
	@Temporal(TemporalType.DATE)
	private Date schedule;
	private int ticketPrice;
	private String place;
	private int maxSeat;
	private int seatsAvailable;
	
	
	
	public int getEventId() {
		return eventId;
	}



	public void setEventId(int eventId) {
		this.eventId = eventId;
	}



	public String getEventName() {
		return eventName;
	}



	public void setEventName(String eventName) {
		this.eventName = eventName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getDuration() {
		return duration;
	}



	public void setDuration(int duration) {
		this.duration = duration;
	}



	public String getEventType() {
		return eventType;
	}



	public void setEventType(String eventType) {
		this.eventType = eventType;
	}



	public Date getSchedule() {
		return schedule;
	}



	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}



	public int getTicketPrice() {
		return ticketPrice;
	}



	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}



	public String getPlace() {
		return place;
	}



	public void setPlace(String place) {
		this.place = place;
	}



	public int getMaxSeat() {
		return maxSeat;
	}



	public void setMaxSeat(int maxSeat) {
		this.maxSeat = maxSeat;
	}



	public int getSeatsAvailable() {
		return seatsAvailable;
	}



	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}



	public EventEntity() {
		super();
	}



	public EventEntity(int eventId, String eventName, String description, int duration, String eventType, Date schedule,
			int ticketPrice, String place, int maxSeat, int seatsAvailable) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.description = description;
		this.duration = duration;
		this.eventType = eventType;
		this.schedule = schedule;
		this.ticketPrice = ticketPrice;
		this.place = place;
		this.maxSeat = maxSeat;
		this.seatsAvailable = seatsAvailable;
	}



	@Override
	public String toString() {
		return "EventEntity [eventId=" + eventId + ", eventName=" + eventName + ", description=" + description
				+ ", duration=" + duration + ", eventType=" + eventType + ", schedule=" + schedule + ", ticketPrice="
				+ ticketPrice + ", place=" + place + ", maxSeat=" + maxSeat + ", seatsAvailable=" + seatsAvailable
				+ "]";
	}
	

}
