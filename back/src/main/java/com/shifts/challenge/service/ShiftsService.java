package com.shifts.challenge.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shifts.challenge.model.Location;
import com.shifts.challenge.model.ShiftsVo;
import com.shifts.challenge.model.TimePunches;
import com.shifts.challenge.model.User;

/**
 * 
 * @author gekson
 *
 */
@Service
public class ShiftsService {
	
	private String URL_LOCATION = "https://shiftstestapi.firebaseio.com/locations.json";
	private String URL_USERS = "https://shiftstestapi.firebaseio.com/users.json";
	private String URL_TIME_PUNCHES = "https://shiftstestapi.firebaseio.com/timePunches.json";

	/**
	 * Get Locations by API
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer, Location> getLocation() {
		RestTemplate restTemplate = new RestTemplate();
		Map<Integer, Location> mapLocation = new HashMap<>();
		
		Map<String, LinkedHashMap<String, Object>> mapObjectLocation = restTemplate.getForObject(URL_LOCATION, Map.class);
		
		for (LinkedHashMap<String, Object> object : mapObjectLocation.values()) {
			Location location = mountLocation(object);
			mapLocation.put(location.getId(), location);
		
		}
		System.out.println(mapObjectLocation.values());
		return mapLocation;
	}

	private Location mountLocation(LinkedHashMap<String, Object> object) {
		Location location = new Location();
		location.setId((Integer)object.get("id"));
		location.setCity((String)object.get("city"));
		location.setDailyOvertimeThreshold((Integer)object.get("dailyOvertimeThreshold"));
		location.setWeeklyOvertimeThreshold((Integer)object.get("weeklyOvertimeThreshold"));
		return location;
	}	
	
	/**
	 * Get Users by API
	 * @return
	 */
	public Map<Integer, User> getUsers() {
		RestTemplate restTemplate = new RestTemplate();
		Map<Integer, User> mapUser = new HashMap<>();
		
		@SuppressWarnings("unchecked")
		Map<String, LinkedHashMap<String, Object>> mapObjectusers = restTemplate.getForObject(URL_USERS, Map.class);
		
		for (LinkedHashMap<String, Object> object : mapObjectusers.values()) {
			mapUser.putAll(mountUser(object));
		}
		
		return mapUser;
	}

	@SuppressWarnings("unchecked")
	private Map<Integer, User> mountUser(LinkedHashMap<String, Object> object) {
		User user = new User();
		Map<Integer, User> mapUser = new HashMap<>();
		
		for (Object obj : object.values()) {
			LinkedHashMap<String, Object> objUser = (LinkedHashMap<String, Object>)obj;
			user.setId((Integer)objUser.get("id"));
			try {
				user.setActive((Boolean)objUser.get("active"));
			} catch (ClassCastException e) {
				user.setActive(false);
			}
			
			user.setHourlyWage((Double)objUser.get("hourlyWage"));
			user.setEmail((String)objUser.get("email"));
			user.setFirstName((String)objUser.get("firstName"));
			user.setPhoto((String)objUser.get("photo"));
			mapUser.put(user.getId(), user);
		}
		return mapUser;
	}	
	
	/**
	 * Get TimePunches by API
	 * @return
	 */
	public Map<Integer, TimePunches> getTimePunches() {
		RestTemplate restTemplate = new RestTemplate();
		Map<Integer, TimePunches> mapTimePunches = new HashMap<>();
		
		@SuppressWarnings("unchecked")
		Map<String, LinkedHashMap<String, Object>> mapObjectTimePunches = restTemplate.getForObject(URL_TIME_PUNCHES, Map.class);
		
		for (LinkedHashMap<String, Object> object : mapObjectTimePunches.values()) {
			TimePunches timePunches = mountTimePunches(object);
			mapTimePunches.put(timePunches.getId(), timePunches);
		}
		
		return mapTimePunches;
	}

	private TimePunches mountTimePunches(LinkedHashMap<String, Object> object) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime clockedIn = null;
		LocalDateTime clockedOut = null;
		try {
			clockedIn = LocalDateTime.parse((String)object.get("clockedIn"), formatter);
	        clockedOut = LocalDateTime.parse((String)object.get("clockedOut"), formatter);
		} catch (DateTimeParseException e) {
			System.out.println("***BEGIN ERROR***");
			System.out.println("ID = " + object.get("id"));
			System.out.println("clockedIn = " + object.get("clockedIn"));
			System.out.println("clockedOut = " + object.get("clockedOut"));	
			System.out.println("***END ERROR***");
		}
        
		TimePunches timePunches = new TimePunches();
		timePunches.setId((Integer)object.get("id"));
		timePunches.setClockedIn(clockedIn);
		timePunches.setClockedOut(clockedOut);
		timePunches.setHourlyWage((Double)object.get("hourlyWage"));
		timePunches.setLocationId((Integer)object.get("locationId"));
		timePunches.setUserId((Integer)object.get("userId"));
		return timePunches;
	}	
	
	public List<ShiftsVo> calulateTimePunches() {
		Map<Integer, TimePunches> shifts = getTimePunches();
		Map<String, Map<String, TimePunches>> resultWeek = new HashMap<>();
		List<ShiftsVo> result = new ArrayList<>();
		LocalDateTime auxDate = null ;
		
		
		populateShifts(shifts, resultWeek, auxDate);
		
		Map<Integer, User> users = getUsers();
		Map<Integer, Location> locations = getLocation();
		
		populateResultWeek(resultWeek, result, users, locations);
		return result;
	}

	private void populateResultWeek(Map<String, Map<String, TimePunches>> resultWeek, List<ShiftsVo> result,
			Map<Integer, User> users, Map<Integer, Location> locations) {
		for (Map<String, TimePunches> times : resultWeek.values()) {
			ShiftsVo shiftsVo = new ShiftsVo();
			for (TimePunches timePunches : times.values()) {
				shiftsVo.setUser(users.get(timePunches.getUserId()));
				shiftsVo.setLocation(locations.get(timePunches.getLocationId()));
				shiftsVo.setWeek(timePunches.getWeekOfYear());
				shiftsVo.setHoursByWeek(shiftsVo.getHoursByWeek()!=null?shiftsVo.getHoursByWeek():0+timePunches.getHoursByDay());
				if(timePunches.getHoursByDay()>480 ) {
					shiftsVo.setHoursPaidByDay(shiftsVo.getHoursPaidByDay()!=null?shiftsVo.getHoursPaidByDay():0+timePunches.getHoursByDay());
				}
			}
			
			if(shiftsVo.getHoursByWeek()>2400 || (shiftsVo.getHoursPaidByDay()!=null&&shiftsVo.getHoursPaidByDay() > 0 )) {
				shiftsVo.setHoursPaid(shiftsVo.getHoursByWeek());
				if(shiftsVo.getHoursPaidByDay() > shiftsVo.getHoursByWeek()) {
					shiftsVo.setHoursPaid(shiftsVo.getHoursPaidByDay());
				}
			}	
			result.add(shiftsVo);
			System.out.println(shiftsVo);
		}
	}

	private void populateShifts(Map<Integer, TimePunches> shifts,
			Map<String, Map<String, TimePunches>> resultWeek, LocalDateTime auxDate) {
		for (TimePunches times : shifts.values()) {
			String key = times.getLocationId().toString()+times.getUserId().toString()+times.getClockedIn().getDayOfMonth()+times.getClockedIn().getMonth().toString()+times.getClockedIn().getYear();
			String keyWeek = times.getLocationId().toString()+times.getUserId().toString()+calculateWeekOfYear(times.getClockedIn())+times.getClockedIn().getYear();
			
			if(auxDate == null) {
				auxDate = times.getClockedIn();
			}
			
			if(resultWeek.containsKey(keyWeek)) {
				if(checkSameWeek(times, auxDate)) {
					if(resultWeek.get(keyWeek).containsKey(key)) {
						resultWeek.get(keyWeek).get(key).setHoursByDay(resultWeek.get(keyWeek).get(key).getHoursByDay()+calculateHours(times));;
					}else {
						resultWeek.get(keyWeek).put(key, times);
						resultWeek.get(keyWeek).get(key).setHoursByDay(calculateHours(times));
					}
				}else {
					Map<String, TimePunches> timeForWeek = new HashMap<>();
					times.setHoursByDay(calculateHours(times));
					times.setWeekOfYear(calculateWeekOfYear(times.getClockedIn()));
					timeForWeek.put(key, times);
					resultWeek.put(keyWeek, timeForWeek);
				}
			}else {
				Map<String, TimePunches> timeForWeek = new HashMap<>();
				times.setHoursByDay(calculateHours(times));
				times.setWeekOfYear(calculateWeekOfYear(times.getClockedIn()));
				timeForWeek.put(key, times);
				resultWeek.put(keyWeek, timeForWeek);
			}
			
			auxDate = times.getClockedIn();
		}
		
	}
	
	private Double calculateHours(TimePunches times) {
		long minutes = 0;
		
		if (times.getClockedOut() != null) {
			minutes = ChronoUnit.MINUTES.between(times.getClockedIn(), times.getClockedOut()); 
		}
		 return new Double(minutes);
	}
	
	private Boolean checkSameWeek(TimePunches times, LocalDateTime previousDate) {
		if(times.getClockedOut() == null) {
			return false;
		}
        Integer weekNumberIn = calculateWeekOfYear(times.getClockedIn());
        Integer weekNumberOut = calculateWeekOfYear(previousDate);
	        
		return weekNumberIn.equals(weekNumberOut);
	}

	private Integer calculateWeekOfYear(LocalDateTime date) {
		WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
		Integer weekNumberIn = date.get(weekFields.weekOfWeekBasedYear());
		return weekNumberIn;
	}

}
