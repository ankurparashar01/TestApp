package com.ibm.model;

public class SendResponse {
	
	private String status;
	private String details;
	
	
	public SendResponse() {
	}
	
	public SendResponse(String status, String details) {
		super();
		this.status = status;
		this.details = details;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
	
	

}
