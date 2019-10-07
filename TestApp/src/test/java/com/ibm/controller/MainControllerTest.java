package com.ibm.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.TestAppBootStrap;
import com.ibm.model.People;
import com.ibm.model.Record;
import com.ibm.model.SendResponse;
import com.ibm.service.RecordService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestAppBootStrap.class, 
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class MainControllerTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	
	@Autowired
	private RecordService recordService;
	
	@Test
	public void getRecord() {
		Record record=  recordService.getRecord();		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> responseEntity = null;
        Record responses= null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        responseEntity = restTemplate.exchange(createURLWithPort("/TestApp/getRecord"), HttpMethod.GET, entity, String.class);
        responses= objectMapper.readValue(responseEntity.getBody(), Record.class);
        }catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("purple-wiki@blocks.com",responses.getEmailAddress() );
        assertEquals("4235-01277-239894",responses.getRequestId() );
	}
	
	@Test
	public void addRecordHappyFlow() {	
		
		Record record = new Record();
		ObjectMapper objectMapper = new ObjectMapper();
		HttpEntity<Record> entity = new HttpEntity<Record>(record, headers);
		SendResponse responses= null;	
		ResponseEntity<String> responseEntity = null;			
		
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
					
		try {
		responseEntity = restTemplate.exchange(createURLWithPort("/TestApp/addRecord"), HttpMethod.POST, entity, String.class);		
		responses= objectMapper.readValue(responseEntity.getBody(), SendResponse.class);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("processed 3 people for purple-wiki@blocks.com",responses.getDetails() );
		assertEquals("OK",responses.getStatus() );
	}
	
	@Test
	public void addRecordNoRequestNumber() {
		
		Record record = new Record();
		//record.setRequestId("4235-01277-239894");
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
				
		HttpEntity<Record> entity = new HttpEntity<Record>(record, headers);		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/TestApp/addRecord"), HttpMethod.POST, entity, String.class);
		System.out.println(response);
		
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
		assertEquals("Failed",response.getBody());
	}
	
	@Test
	public void addRecordNoEmail() {
		
		Record record = new Record();
		record.setRequestId("4235-01277-239894");
		//record.setEmailAddress("purple-wiki@blocks.com");
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
				
		HttpEntity<Record> entity = new HttpEntity<Record>(record, headers);		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/TestApp/addRecord"), HttpMethod.POST, entity, String.class);
		System.out.println(response);
		
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
		assertEquals("Failed",response.getBody());
	}
	
	
	@Test
	public void addRecordNoPeopleRecord() {
		
		Record record = new Record();
		record.setRequestId("4235-01277-239894");
		record.setEmailAddress("purple-wiki@blocks.com");
		List<People> peoplelist = new ArrayList<People>();		
		/*
		 * People nameOne= new People(); 
		 * nameOne.setName("John Smith"); 
		 * People nameTwo=new People(); 
		 * nameTwo.setName("Willy Wonka"); 
		 * People nameThree= new People();
		 * nameThree.setName("Margaret Watson"); 
		 * peoplelist.add(nameOne);
		 * peoplelist.add(nameTwo); 
		 * peoplelist.add(nameThree);
		 * record.setPepole(peoplelist);
		 */
				
		HttpEntity<Record> entity = new HttpEntity<Record>(record, headers);		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/TestApp/addRecord"), HttpMethod.POST, entity, String.class);
		System.out.println(response);
		
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
		assertEquals("Failed",response.getBody());
	}
	
	
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	
}
