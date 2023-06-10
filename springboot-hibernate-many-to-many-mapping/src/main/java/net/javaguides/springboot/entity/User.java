package net.javaguides.springboot.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {
	

    private long id;
	

    private String name;
	

    private String email;
	

	private Set<Hotel> hotels = new HashSet<>();
    
    public User() {
    	
    }

	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_hotel_assignments",
			joinColumns = { @JoinColumn(name = "user_id")},
			inverseJoinColumns = { @JoinColumn (name = "hotel_id")})
	public Set<Hotel> getHotels() {
		return hotels;
	}
	public void setHotels(Set<Hotel> hotels) {
		this.hotels = hotels;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
