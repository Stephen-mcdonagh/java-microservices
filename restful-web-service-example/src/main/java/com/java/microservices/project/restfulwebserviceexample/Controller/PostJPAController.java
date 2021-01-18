package com.java.microservices.project.restfulwebserviceexample.Controller;

import com.java.microservices.project.restfulwebserviceexample.ExceptionHandler.UserNotFoundException;
import com.java.microservices.project.restfulwebserviceexample.Model.Post;
import com.java.microservices.project.restfulwebserviceexample.Model.User;
import com.java.microservices.project.restfulwebserviceexample.Repository.PostRepository;
import com.java.microservices.project.restfulwebserviceexample.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostJPAController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getAllPostsForUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()){
			throw new UserNotFoundException("id = "+ id);
		}
		return user.get().getPosts();
	}

//	todo: fix get request loop for this http://localhost:8080/jpa/users/1000001
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @RequestBody Post post){
		Optional<User> optionalUser =  userRepository.findById(id);
		if(!optionalUser.isPresent()){
			throw new UserNotFoundException("id = " + id);
		}

		User user = optionalUser.get();
		post.setUser(user);
		postRepository.save(post);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
