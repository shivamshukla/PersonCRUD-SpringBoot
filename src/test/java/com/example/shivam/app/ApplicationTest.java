package com.example.shivam.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.shivam.domain.Person;
import com.example.shivam.domain.PersonRepository;
import com.example.shivam.domain.User;
import com.example.shivam.domain.UserRepository;

public class ApplicationTest {
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository =personRepository;
    }    
    
    @Test
    public void addUser() {
    	User user = new User("testuser", "testuser", "USER");

    	assertNull(user.getId());
    	userRepository.save(user);
    	assertNotNull(user.getId());
    }
    
	@Test
    public void addPerson() {
		Person person = new Person("Test", "Test1", "IT", "red","basketball");
		
		personRepository.save(person);
		Optional<Person> findPerson = personRepository.findById(person.getId());
		assertTrue(findPerson.isPresent());
    }
    
}

