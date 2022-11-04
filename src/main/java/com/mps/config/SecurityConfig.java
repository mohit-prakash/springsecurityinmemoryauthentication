package com.mps.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//InMemoryAuthentication (Defined usr/pwd/Role)
		auth.inMemoryAuthentication().withUser("mohit").password("{noop}prakash").authorities("ADMIN");
		auth.inMemoryAuthentication().withUser("pratik").password("{noop}prakash").authorities("EMPLOYEE");
		auth.inMemoryAuthentication().withUser("mps").password("{noop}mps").authorities("STUDENT");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//used for Authorization (url and their access defined)
		http.authorizeRequests()
		//URL-Access type
		.antMatchers("/home").permitAll()
		.antMatchers("/welcome").authenticated()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/std").hasAuthority("STUDENT")
		.antMatchers("/emp").hasAuthority("EMPLOYEE")
		//login form
		.and()
		.formLogin()
		.defaultSuccessUrl("/welcome", true)
		//logout
		.and()
		.logout()
		//Exception Handling (403 handling)
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied")
		;
	}
}
