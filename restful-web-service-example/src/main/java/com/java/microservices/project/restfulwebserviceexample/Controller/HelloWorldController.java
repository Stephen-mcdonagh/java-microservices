package com.java.microservices.project.restfulwebserviceexample.Controller;

import com.java.microservices.project.restfulwebserviceexample.Bean.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

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

	@GetMapping(path="/hello-i18n")
	public String helloWorkInternationalIzation( Locale locale){
		return messageSource.getMessage("hello.message",null, LocaleContextHolder.getLocale());
	}

}
