package com.ibm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.model.People;
import com.ibm.model.Record;
import com.ibm.model.SendResponse;

@Service
public class RecordService {

	public Record getRecord() {
		
		Record record = new Record();
		
		record.setRequestId("4235-01277-239894");
		record.setEmailAddress("purple-wiki@blocks.com");
				
		People nameOne= new People();
		nameOne.setName("John Smith");
		
		People nameTwo= new People();
		nameTwo.setName("Willy Wonka");
		
		People nameThree= new People();
		nameThree.setName("Margaret Watson");
		
		List<People> peoplelist = new ArrayList<People>();
		peoplelist.add(nameOne);
		peoplelist.add(nameTwo);
		peoplelist.add(nameThree);
		
		record.setPepole(peoplelist);	
		
		return record;
	}

	public SendResponse addRecord(Record inputData) {
		return new SendResponse("OK", "processed 3 people for purple-wiki@blocks.com");
		
		
	}
	

	

}
