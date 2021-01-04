package com.java.microservices.project.restfulwebserviceexample.Controller;

import com.java.microservices.project.restfulwebserviceexample.Bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping( path = "/hello")
	public String getHelloWorld (){
		return "hello World !";
	}

	@GetMapping( path = "/hello-bean")
	public HelloWorldBean getHelloWorldBean (){
		return new HelloWorldBean("Bean Test");
	}

	@GetMapping( path = "/hello-bean/path/{name}")
	public HelloWorldBean getHelloWorldBeanPathVariable (@PathVariable String name){
		return new HelloWorldBean(String.format("hello World %s", name));
	}


}
