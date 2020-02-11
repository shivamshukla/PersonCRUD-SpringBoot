package com.example.shivam.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.shivam.domain.SignupForm;
import com.example.shivam.domain.User;
import com.example.shivam.domain.UserRepository;

/**
 * Class for user related operation
 * @author shivam
 *
 */
@Controller
public class UserController {

	@Autowired
    private UserRepository repository;
	
	/**
	 * Add person in the application
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "signup")
    public String addPerson(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }
	
	/**
	 * Save the person in the application 
	 * @param signupForm
	 * @param bindingResult
	 * @return
	 */
	 @RequestMapping(value = "saveuser", method = RequestMethod.POST)
	    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
	    	System.out.println(bindingResult.toString());
	    	if (!bindingResult.hasErrors()) { // validation errors
	    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
		    		String pwd = signupForm.getPassword();
			    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			    	String hashPwd = bc.encode(pwd);
		
			    	User newUser = new User();
			    	newUser.setPasswordHash(hashPwd);
			    	newUser.setUsername(signupForm.getUsername());
			    	newUser.setRole("USER");
			    	if (repository.findByUsername(signupForm.getUsername()) == null) {
			    		repository.save(newUser);
			    	}
			    	else {
		    			bindingResult.rejectValue("username", "error.userexists", "Username already exists");    	
		    			return "signup";		    		
			    	}
	    		}
	    		else {
	    			bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords does not match");    	
	    			return "signup";
	    		}
	    	}
	    	else {
	    		return "signup";
	    	}
	    	return "redirect:/login";    	
	    }  
}
