package com.java.microservices.project.restfulwebserviceexample.Repository;
import com.java.microservices.project.restfulwebserviceexample.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

}
