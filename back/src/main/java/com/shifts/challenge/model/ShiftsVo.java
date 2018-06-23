package com.shifts.challenge.model;

import java.util.List;

public class ShiftsVo {

	private User user;
	private Location location;
	private Integer week;
	private Double hoursByWeek;
	private Double hoursPaidByDay;
	private Double hoursPaid;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	
	public Double getHoursByWeek() {
		return hoursByWeek;
	}
	public void setHoursByWeek(Double hoursByWeek) {
		this.hoursByWeek = hoursByWeek;
	}
	public Double getHoursPaidByDay() {
		return hoursPaidByDay;
	}
	public void setHoursPaidByDay(Double hoursPaidByDay) {
		this.hoursPaidByDay = hoursPaidByDay;
	}
	public Double getHoursPaid() {
		return hoursPaid;
	}
	public void setHoursPaid(Double hoursPaid) {
		this.hoursPaid = hoursPaid;
	}
	@Override
	public String toString() {
		return "ShiftsVo [user=" + user.getId() + ", location=" + location.getId() + ", week=" + week + ", hoursByWeek=" + hoursByWeek
				+ ", "  + ", hoursPaidByDay=" + hoursPaidByDay + ", hoursPaid=" + hoursPaid
				+ "]";
	}
	
}
