package com.java.microservices.project.restfulwebserviceexample.Repository;
import com.java.microservices.project.restfulwebserviceexample.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
