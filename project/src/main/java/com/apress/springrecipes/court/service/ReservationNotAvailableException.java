package com.apress.springrecipes.court.service;

import java.util.Date;

public class ReservationNotAvailableException extends RuntimeException {

	private String courtName;
	private Date date;
	private int hour;
	
	public ReservationNotAvailableException(String courtName, Date date,
			int hour) {
		// TODO Auto-generated constructor stub
		this.courtName=courtName;
		this.date=date;
		this.hour=hour;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	

}
