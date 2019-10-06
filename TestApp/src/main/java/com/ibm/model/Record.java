package com.ibm.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Record {
	
	@NotNull(message="requestId cannot be empty")
	private String requestId;
	
	@NotNull(message="emailAddress cannot be empty")
	@Email(message="emailAddress must be a valid email format")
	private String emailAddress;
	
	@NotNull(message="people cannot be empty")
	private List<People> people;
	
	public Record() {
		super();		
	}
	
	public Record(String requestId, String emailAddress, List<People> pepole) {
		super();
		this.requestId = requestId;
		this.emailAddress = emailAddress;
		this.people = people;
	}
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public List<People> getPepole() {
		return people;
	}
	public void setPepole(List<People> pepole) {
		this.people = pepole;
	}
	
	@Override
	public String toString() {
		return "InputData [requestId=" + requestId + ", emailAddress=" + emailAddress + ", people=" + people + "]";
	}
}
