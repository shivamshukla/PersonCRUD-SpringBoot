package com.example.shivam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.shivam.service.UserServiceImpl;

/**
 * Web Security 
 * @author shivam
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	 @Autowired
	    private UserServiceImpl userDetailsService;
	 
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	    
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	http
	    		.authorizeRequests().antMatchers(
                        "/css/**", "/signup", "/saveuser").permitAll() // Enable css when logged out
	    			.and()
	            .authorizeRequests()
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	               	.loginPage("/login")
	                .successForwardUrl("/persons")
	                .permitAll()
	            	.and()
	            .logout()
	            	.permitAll()
	            	.and();
	    }
		
		 @Override
		    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		        auth.authenticationProvider(authenticationProvider());
		    }
		 
		 @Bean
		    public DaoAuthenticationProvider authenticationProvider(){
		        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		        auth.setUserDetailsService(userDetailsService);
		        auth.setPasswordEncoder(passwordEncoder());
		        return auth;
		    }
	     
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	    }
	}