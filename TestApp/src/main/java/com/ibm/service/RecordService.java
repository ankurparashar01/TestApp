package com.ibm.service;

import com.ibm.model.Record;
import com.ibm.model.RecordServiceResponse;

public interface RecordService {
	
	public Record getRecord();
	public RecordServiceResponse addRecord(Record inputData);

}
