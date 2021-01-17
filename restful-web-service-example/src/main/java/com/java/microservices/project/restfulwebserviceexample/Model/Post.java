package com.java.microservices.project.restfulwebserviceexample.Model;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {

	@Getter @Setter @Id @GeneratedValue
	private Integer id;
	@Getter @Setter
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@Getter@Setter
	private User user;

	@Override
	public String toString(){
		return String.format("Post [id=%s,name=%s, description=%s]", id,description);
	}
}
