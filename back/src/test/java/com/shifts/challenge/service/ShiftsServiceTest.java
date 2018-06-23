package com.shifts.challenge.service;

import static org.junit.Assert.assertNotNull;

import java.time.temporal.ChronoUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiftsServiceTest {
	@Autowired
	ShiftsService service;

	@Test
	public void testLocation() {
		assertNotNull(service.getLocation());
	}

	@Test
	public void testUser() {
		assertNotNull(service.getUsers());
	}
	
	@Test
	public void testTimePunches() {
		assertNotNull(service.getTimePunches());
	}

	@Test
	public void tesCcalulateTimePunches() {
		assertNotNull(service.calulateTimePunches());
	}
	
}
