package com.java.microservices.project.restfulwebserviceexample.ExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	//ex.getMessage can be changed to not include so much info
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		ExceptionResponse response = new ExceptionResponse(
				new Date(),
//				ex.getMessage(),
				"Validation failed ",
				ex.getBindingResult().toString()
		);

		return new ResponseEntity(response,HttpStatus.BAD_REQUEST);	}

	//TODO: Add extra cases
}
