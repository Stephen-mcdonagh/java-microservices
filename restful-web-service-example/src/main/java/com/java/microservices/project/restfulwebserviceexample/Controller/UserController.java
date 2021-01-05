package com.java.microservices.project.restfulwebserviceexample.Controller;
import com.java.microservices.project.restfulwebserviceexample.ExceptionHandler.UserNotFoundException;
import com.java.microservices.project.restfulwebserviceexample.Model.User;
import com.java.microservices.project.restfulwebserviceexample.Service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
		User user =  userDaoService.findUserById(id);
		if(user==null){
			throw new UserNotFoundException("id = " + id);
		}

		return user;
	}

	@PostMapping("/users")
	public ResponseEntity cerateUser(@RequestBody User user){
		User savedUser = userDaoService.saveUser(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
