package com.example.shivam.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repo class for person
 * @author shivam
 *
 */
@Repository
public interface PersonRepository  extends CrudRepository<Person, Long> {

    List<Person> findByLastName(String lastname);
}
