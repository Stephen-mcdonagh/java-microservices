package com.java.microservices.project.restfulwebserviceexample.Controller;
import com.java.microservices.project.restfulwebserviceexample.ExceptionHandler.UserNotFoundException;
import com.java.microservices.project.restfulwebserviceexample.Model.User;
import com.java.microservices.project.restfulwebserviceexample.Service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
	public EntityModel<User> retrieveUser(@PathVariable int id){
		User user =  userDaoService.findUserById(id);
		if(user==null){
			throw new UserNotFoundException("id = " + id);
		}

		//Returns back a link to all the users too
		EntityModel<User> resource = EntityModel.of(user);

		WebMvcLinkBuilder linkTo =
				linkTo(methodOn(this.getClass()).getAllUsers());

		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@PostMapping("/users")
	public ResponseEntity createUser(@Valid @RequestBody User user){
		User savedUser = userDaoService.saveUser(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		User userToBeDeleted = userDaoService.deleteById(id);
		if(userToBeDeleted == null){
			throw new UserNotFoundException("id- " + id);
		}
	}
}
