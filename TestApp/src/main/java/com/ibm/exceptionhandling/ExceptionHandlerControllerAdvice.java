package com.ibm.exceptionhandling;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ibm.model.SendResponse;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(RecordException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody SendResponse handleResourceNotFound(final RecordException exception,
			final HttpServletRequest request) {
 
		/*ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());*/
		
		SendResponse sendResponse = new SendResponse();
		sendResponse.setDetails("Failed1");
		sendResponse.setStatus("Failed2");
		return sendResponse;
	}

	/*
	 * @ExceptionHandler(Exception.class)
	 * 
	 * @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	 * public @ResponseBody ExceptionResponse handleException(final Exception
	 * exception, final HttpServletRequest request) {
	 * 
	 * ExceptionResponse error = new ExceptionResponse();
	 * error.setErrorMessage(exception.getMessage());
	 * error.callerURL(request.getRequestURI());
	 * 
	 * return error; }
	 */

}