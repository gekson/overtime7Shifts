package com.shifts.challenge.model;

public class Location {
	private Integer id;
	private String city;
	private Integer dailyOvertimeThreshold;
	private Integer weeklyOvertimeThreshold;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getDailyOvertimeThreshold() {
		return dailyOvertimeThreshold;
	}

	public void setDailyOvertimeThreshold(Integer dailyOvertimeThreshold) {
		this.dailyOvertimeThreshold = dailyOvertimeThreshold;
	}

	public Integer getWeeklyOvertimeThreshold() {
		return weeklyOvertimeThreshold;
	}

	public void setWeeklyOvertimeThreshold(Integer weeklyOvertimeThreshold) {
		this.weeklyOvertimeThreshold = weeklyOvertimeThreshold;
	}

}
