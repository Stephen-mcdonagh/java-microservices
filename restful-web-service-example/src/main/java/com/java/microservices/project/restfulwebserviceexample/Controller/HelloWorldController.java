package com.java.microservices.project.restfulwebserviceexample.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET, path = "/hello")
	public String getHelloWorld (){
		return "hello World !";
	}

}
