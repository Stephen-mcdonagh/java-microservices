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
