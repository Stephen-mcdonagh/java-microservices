package com.java.microservices.project.restfulwebserviceexample.ExceptionHandler;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ExceptionResponse {

	@Getter @Setter
	private Date timestamp;
	@Getter @Setter
	private String message;
	@Getter @Setter
	private String details;

	public ExceptionResponse(Date timestamp,String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
}
