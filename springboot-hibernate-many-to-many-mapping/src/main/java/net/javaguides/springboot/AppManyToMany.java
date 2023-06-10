package net.javaguides.springboot;

import net.javaguides.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import net.javaguides.springboot.entity.Hotel;
import net.javaguides.springboot.repository.UserRepository;

import java.util.Set;

//@SpringBootApplication
public class AppManyToMany implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppManyToMany.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = new User("Ankit" , "ankit@gmail.com");
		User user1 = new User("Ishan", "ishan@gmail.com");

		Hotel hotel1 = new Hotel("Hotel 1");
		Hotel hotel2 = new Hotel("Hotel 2");

		// add tag references post
		user.getHotels().add(hotel1);
		user.getHotels().add(hotel2);

		// add post references tag

		hotel1.getUsers().add(user);
		hotel2.getUsers().add(user);

		hotel1.getUsers().add(user1);
		user1.getHotels().add(hotel1);


		this.userRepository.save(user);
		this.userRepository.save(user1);

		Set<Hotel> hotels1 = this.userRepository.findById(1L).get().getHotels();
		hotels1.stream().forEach(System.out::println);
		System.out.println();

		Set<Hotel> hotels2 = this.userRepository.findById(2L).get().getHotels();
		hotels2.stream().forEach(System.out::println);
		System.out.println();

	}

}
