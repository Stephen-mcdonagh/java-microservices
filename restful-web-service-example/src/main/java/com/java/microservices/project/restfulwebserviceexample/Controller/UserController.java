package com.java.microservices.project.restfulwebserviceexample.Controller;
import com.java.microservices.project.restfulwebserviceexample.Model.User;
import com.java.microservices.project.restfulwebserviceexample.Service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;
	//Get users
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userDaoService.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id){
		return userDaoService.findUserById(id);
	}
	//get specific user

	//save user
}
