package com.shifts.challenge.model;

import java.time.LocalDate;

public class User {

	private Boolean active;
    private LocalDate created;
    private String email;
    private String firstName;
    private Double hourlyWage;
    private Integer id;
    private String lastName;
    private Integer locationId;
    private LocalDate modified;
    private String photo;
    private Integer userType;
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public LocalDate getCreated() {
		return created;
	}
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public LocalDate getModified() {
		return modified;
	}
	public void setModified(LocalDate modified) {
		this.modified = modified;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
    
    
}
