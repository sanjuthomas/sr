package com.scrumretro.rest;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.scrumretro.enums.ResponseStatus;
import com.scrumretro.exception.ScrumRetroRuntimeException;

/**
 * 
 * Super class for all services.
 * 
 * @author Sanju Thomas
 *
 */
public class Service {
	
	/**
	 * This method shall handle the ScrumRetroRuntimeException
	 * 
	 * @return response
	 */
	@ExceptionHandler(ScrumRetroRuntimeException.class)
	public Response handleScrumRetroRuntimeException(final ScrumRetroRuntimeException scrumRetroRuntimeException){
		
		final Response response = new Response();
		response.setErrorCode(scrumRetroRuntimeException.getErrorCode());
		response.setMessage(scrumRetroRuntimeException.getMessage());
		response.setStatus("failed");
		return response;
	}
	
	/**
	 * 
	 * This method shall handle all generic Exception
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public Response handleGenericException(final Exception exception){
		final Response response = new Response();
		response.setErrorCode(ResponseStatus.UNKNOWN_ERROR.getCode());
		response.setMessage("System error. Please report to support@scrumretro.com");
		response.setStatus("error");
		return response;
	}
}
