package com.shifts.challenge.model;

import java.time.LocalDateTime;

public class TimePunches implements Comparable<TimePunches>{

	private LocalDateTime clockedIn;
	private LocalDateTime clockedOut;
	private LocalDateTime created;
	private Double hourlyWage;
    private Integer id;
    private Integer locationId;
    private Integer modified;
    private Integer userId;
    private Double hoursByDay;
    private Double hoursByWeek;
    private Integer weekOfYear;
    
	public LocalDateTime getClockedIn() {
		return clockedIn;
	}
	public void setClockedIn(LocalDateTime clockedIn) {
		this.clockedIn = clockedIn;
	}
	public LocalDateTime getClockedOut() {
		return clockedOut;
	}
	public void setClockedOut(LocalDateTime clockedOut) {
		this.clockedOut = clockedOut;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public Double getHourlyWage() {
		return hourlyWage;
	}
	public void setHourlyWage(Double hourlyWage) {
		this.hourlyWage = hourlyWage;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public Integer getModified() {
		return modified;
	}
	public void setModified(Integer modified) {
		this.modified = modified;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Double getHoursByDay() {
		return hoursByDay;
	}
	public void setHoursByDay(Double hoursByDay) {
		this.hoursByDay = hoursByDay;
	}
	public Double getHoursByWeek() {
		return hoursByWeek;
	}
	public void setHoursByWeek(Double hoursByWeek) {
		this.hoursByWeek = hoursByWeek;
	}
	
	public Integer getWeekOfYear() {
		return weekOfYear;
	}
	public void setWeekOfYear(Integer weekOfYear) {
		this.weekOfYear = weekOfYear;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clockedIn == null) ? 0 : clockedIn.hashCode());
		result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimePunches other = (TimePunches) obj;
		if (clockedIn == null) {
			if (other.clockedIn != null)
				return false;
		} else if (!clockedIn.equals(other.clockedIn))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(TimePunches o) {
		if(o.getClockedIn().getDayOfMonth() == this.getClockedIn().getDayOfMonth() && 
				o.getUserId() == this.getUserId() && o.getLocationId() == this.locationId) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return this.id.toString() + " USER: " +this.userId + " Hour by Day: " + this.hoursByDay +" Week: "+ this.weekOfYear+ " Hour By Week: " + this.hoursByWeek + " clockedIn: " + clockedIn;
	}
	
    
}
