package net.javaguides.springboot.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "hotels")
public class Hotel {
	

	private long id;
	private String name;
	

	private Set<User> users = new HashSet<>();
	
	public Hotel() {
		
	}
	
	public Hotel(String name) {
		super();
		this.name = name;
	}
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "hotels")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
