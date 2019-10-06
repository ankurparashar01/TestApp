package com.ibm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.model.Record;
import com.ibm.model.SendResponse;
import com.ibm.service.RecordService;
import com.ibm.service.RecordServiceException;

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
	
	
	
	@RequestMapping(method = RequestMethod.POST,value="/addRecord1")
	public SendResponse addRecord1(@Valid @RequestBody Record inputData, Errors errors ) {		
		if(errors.hasErrors()) {
			return new SendResponse("Failed", "Unable to processed");
		}		
		return recordService.addRecord(inputData);
	}
	
	
	@RequestMapping(method = RequestMethod.POST,value="/addRecord2")
	public SendResponse addRecord2(@Valid @RequestBody Record inputData) {		
		return recordService.addRecord(inputData);
	}
	
	
	
	
	@RequestMapping(value = "/noRecord", method = RequestMethod.GET)
	public SendResponse noRecordCheck() throws com.ibm.exceptionhandling.RecordException, RecordServiceException {
		try {
			SendResponse response = recordService.getRecordNull();
			if (response == null) {
				throw new com.ibm.exceptionhandling.RecordException("Input Record not found");
			}

			return response;
		} catch (RecordServiceException e) {
			throw new RecordServiceException("Internal Server Exception while getting exception");
		}
	}
	
	
	
	

}
