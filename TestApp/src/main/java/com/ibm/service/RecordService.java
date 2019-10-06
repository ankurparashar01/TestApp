package com.ibm.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.ibm.model.Record;
import com.ibm.model.People;
import com.ibm.model.SendResponse;

@Service
public class RecordService {

	public Record returnData() {
		
		Record inputData = new Record();
		
		inputData.setRequestId("4235-01277-239894");
		inputData.setEmailAddress("purple-wiki@blocks.com");
				
		People nameOne= new People();
		nameOne.setName("John Smith");
		
		People nameTwo= new People();
		nameTwo.setName("Willy Wonka");
		
		People nameThree= new People();
		nameThree.setName("Margaret Watson");
		
		List<People> peoples = new ArrayList<People>();
		peoples.add(nameOne);
		peoples.add(nameTwo);
		peoples.add(nameThree);
		
		inputData.setPepole(peoples);	
		
		return inputData;
	}

	public SendResponse addRecord(Record inputData) {
		return new SendResponse("OK", "processed 3 people for purple-wiki@blocks.com");
		
		
	}
	
	public SendResponse getRecordNull() throws RecordServiceException {
		return null;
	}
	
	public SendResponse getRecordException() throws RecordServiceException {
		throw new RecordServiceException();
	}
	

}
