package com.java.microservices.project.restfulwebserviceexample.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
/*
	Playing around with exception handling for specific cases
	Can use format I want for exceptions - easier to debug
*/
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request){
		ExceptionResponse response = new ExceptionResponse(
				new Date(),
				e.getMessage(),
				request.getDescription(false)
		);

		return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception userNotFoundException, WebRequest request){
		ExceptionResponse response = new ExceptionResponse(
				new Date(),
				userNotFoundException.getMessage(),
				request.getDescription(false)
		);

		return new ResponseEntity(response,HttpStatus.NOT_FOUND);
	}

	//TODO: Add extra cases
}
