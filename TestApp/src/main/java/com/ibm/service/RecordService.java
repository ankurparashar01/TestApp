package com.ibm.service;

import com.ibm.model.Record;
import com.ibm.model.SendResponse;

public interface RecordService {
	
	public Record getRecord();
	public SendResponse addRecord(Record inputData);

}
