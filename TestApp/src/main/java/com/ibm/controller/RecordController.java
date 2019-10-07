package com.ibm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.model.Record;
import com.ibm.service.RecordService;
import com.ibm.service.RecordServiceImpl;

@RestController
@RequestMapping(value="/TestApp")
public class RecordController {
	
	@Autowired
	private RecordService recordService;
	
	@RequestMapping("/welcome")
	public String Welcome() {
		return "Welcome to Test App - a sample application....!!!!!!!!!!!" ;	}
	
	
	// print input record data in json format
	@GetMapping("/record")
	public Record getRecord() {
		return recordService.getRecord();			
	}	

	// will be a post request and it will take record from request & process it.	
	@PostMapping("/record")
	//@RequestMapping(method = RequestMethod.POST,value="/record")
	public Object addRecord(@Valid @RequestBody Record record, Errors errors ) {		
		if(errors.hasErrors()) {
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}		
		return recordService.addRecord(record);
	}
	
}
