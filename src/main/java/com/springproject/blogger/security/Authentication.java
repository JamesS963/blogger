package com.springproject.blogger.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.springproject.blogger.model.User;

public class Authentication {

	CustomUserDetails user;
	
	public Authentication() {
		super();
		user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public User getLoggedInUser() {
		
		return user.getAccount();
	}
}
