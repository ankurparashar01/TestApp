package com.ibm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.model.Record;
import com.ibm.model.SendResponse;
import com.ibm.service.RecordService;


@RestController
public class MainController {
	
	@Autowired
	private RecordService recordService;
	
	@RequestMapping("/welcome")
	public String Welcome() {
		return "Welcome to Test App - a sample application....!!!!!!!!!!!" ;
	}
	
	
	// print input record data in json format
	@RequestMapping("/getRecord")
	public Record getData() {
		return recordService.returnData();			
	}	

	// will be a post request and it will take record from request & process it.	
	@RequestMapping(method = RequestMethod.POST,value="/addRecord")
	public Object addRecord(@Valid @RequestBody Record inputData, Errors errors ) {		
		if(errors.hasErrors()) {
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}		
		return new SendResponse("OK", "processed 3 people for purple-wiki@blocks.com");
	}
	
}
