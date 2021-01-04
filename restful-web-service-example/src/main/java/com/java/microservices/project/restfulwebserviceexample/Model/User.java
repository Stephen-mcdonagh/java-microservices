package com.java.microservices.project.restfulwebserviceexample.Model;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//Might get error with no construcor (Step 07)
public class User {

	private Integer id;
	private String name;
	private Date dateOfBirth;

	public User(int i, String stephen, Date date) {
	}
}
