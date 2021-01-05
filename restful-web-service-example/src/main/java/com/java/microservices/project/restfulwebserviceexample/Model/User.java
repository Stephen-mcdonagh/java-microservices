package com.java.microservices.project.restfulwebserviceexample.Model;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
//Might get error with no construcor (Step 07)
public class User {

	private Integer id;
	@Size(min = 2, max = 30, message = "Name should have at least 2 characters and no more than 30")
	private String name;
	@Past(message = "Date of birth must be in the past")
	private Date dateOfBirth;

	protected User(){}

	public User(Integer id, String name, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString(){
		return String.format("User [id=%s,name=%s, dateOfBirth=%s]", id,name,dateOfBirth);
	}
}
