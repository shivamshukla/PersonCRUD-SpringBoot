package com.example.shivam.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.shivam.domain.Person;
import com.example.shivam.domain.PersonRepository;

/**
 * Controller class for person related operation
 * @author shivam
 *
 */
@Controller
public class PersonController {
	
	@Autowired
	PersonRepository personRepository;
	
	/**
	 * Login method
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
    	return "login";
    }
	
	/**
	 * Show person details
	 * @param model
	 * @return
	 */
	@RequestMapping("/persons")
	public String showPerson(Model model) {
		List<Person> personList = (List<Person>)personRepository.findAll();
		model.addAttribute("persons",personList);
		return "persons";
	}
	
	/**
	 * Add the person
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String addPerson(Model model) {
			model.addAttribute("person", new Person());
		return "addperson";
	}
	
	/**
	 * Edit the person
	 * @param personId
	 * @param model
	 * @return
	 */
	 @RequestMapping(value = "/edit/{id}")
	    public String editPerson(@PathVariable("id") Long personId, Model model){
	    	model.addAttribute("person", personRepository.findById(personId));
	        return "editperson";
	    }	    
	    
	 /**
	  * Save the person details
	  * @param person
	  * @return
	  */
	    @RequestMapping(value = "save", method = RequestMethod.POST)
	    public String save(Person person){
	        personRepository.save(person);
	    	return "redirect:/persons";
	    }
	    
	    /**
	     * Delete the person details
	     * @param personId
	     * @param model
	     * @return
	     */
	    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deletePerson(@PathVariable("id") Long personId, Model model) {
	    	personRepository.deleteById(personId);
	        return "redirect:/persons";
	    }
	    
	    /**
	     * 
	     * @return
	     */
	    @RequestMapping(value = "getpersons", method = RequestMethod.GET)
	    public @ResponseBody List<Person> getPersons() {
	            return (List<Person>)personRepository.findAll();
	    }    

}
