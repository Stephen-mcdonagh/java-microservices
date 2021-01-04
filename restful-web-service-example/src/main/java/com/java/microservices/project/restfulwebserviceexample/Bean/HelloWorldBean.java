package com.java.microservices.project.restfulwebserviceexample.Bean;
import lombok.Getter;
import lombok.Setter;

public class HelloWorldBean {

	@Getter
	@Setter
	private String message;

	public HelloWorldBean(String message){
		this.message = message;
	}

	@Override
	public String toString(){
		return String.format("HelloWorldBean [message=%s]",message);
	}
}
