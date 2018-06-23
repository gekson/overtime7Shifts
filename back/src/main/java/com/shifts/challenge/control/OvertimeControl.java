package com.shifts.challenge.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.challenge.model.ShiftsVo;
import com.shifts.challenge.model.TimePunches;
import com.shifts.challenge.service.ShiftsService;

@RestController
public class OvertimeControl {

	@Autowired
	ShiftsService service;
	
	@GetMapping("/overtime")
    @ResponseBody
    public ResponseEntity<List<ShiftsVo>> allCandidatos() {
        List<ShiftsVo> listCResult= null;

        try {
        	listCResult = service.calulateTimePunches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ResponseEntity<>(listCResult, HttpStatus.OK);
    }
}
