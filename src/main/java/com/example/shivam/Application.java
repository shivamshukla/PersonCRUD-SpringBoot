package com.example.shivam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.shivam.domain.Person;
import com.example.shivam.domain.PersonRepository;
import com.example.shivam.domain.User;
import com.example.shivam.domain.UserRepository;

/**
 * Main entry class for thee application
 * @author shivam
 *
 */
@SpringBootApplication
@ComponentScan("com.example.shivam")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Save demo users, courses and persons to H2 DB
	 * @param repository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(PersonRepository personRepository, UserRepository userRerepository) {
		return (args) -> {
			// save Persons
			personRepository.save(new Person("John", "Wick", "29", "Red","Shooting,Shooting")); 
			personRepository.save(new Person("Tony", "Stark", "30", "Gold","Flying,Inventor,Avengers"));
			personRepository.save(new Person("Captain", "America", "31", "Blue","Planning,Leader,Avengers"));
			personRepository.save(new Person("Natasha", "Romanav", "32", "Black","Widow,Witch"));
			personRepository.save(new Person("Ant", "Man", "33", "Brown","Small,Handy"));
			
			// Create users with BCrypt encoded password (user/user, admin/admin)
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			userRerepository.save(user1);
			userRerepository.save(user2); 
		};
	}
}
