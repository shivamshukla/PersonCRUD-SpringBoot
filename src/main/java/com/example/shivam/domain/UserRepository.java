package com.example.shivam.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repo class for the user
 * @author shivam
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
    User findByUsername(String username);

}

