package com.java.microservices.project.restfulwebserviceexample.Controller;
import com.java.microservices.project.restfulwebserviceexample.ExceptionHandler.UserNotFoundException;
import com.java.microservices.project.restfulwebserviceexample.Model.User;
import com.java.microservices.project.restfulwebserviceexample.Repository.PostRepository;
import com.java.microservices.project.restfulwebserviceexample.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	//Get users
	@GetMapping("/jpa/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
		Optional<User> user =  userRepository.findById(id);
		if(!user.isPresent()){
			throw new UserNotFoundException("id = " + id);
		}

		//Returns back a link to all the users too
		EntityModel<User> resource = EntityModel.of(user.get());

		WebMvcLinkBuilder linkTo =
				linkTo(methodOn(this.getClass()).getAllUsers());

		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity createUser(@Valid @RequestBody User user){
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
	}
}
