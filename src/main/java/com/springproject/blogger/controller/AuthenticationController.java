package com.springproject.blogger.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.blogger.model.User;
import com.springproject.blogger.service.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/login")
	public ModelAndView getLoginPage() {
		return new ModelAndView("login", "user", new User());
	}
	
	@GetMapping(value = "/loginFailed")
	public ModelAndView loginFailed(HttpServletRequest request) {
		return new ModelAndView("login", "user", new User()).addObject("error", "Login Credentials are incorrect, please try again.");
	}

	@GetMapping(value = "/register")
	public ModelAndView getRegisterPage() {
		return new ModelAndView("register", "user", new User());
	}

	@PostMapping("/register")
	public ModelAndView postRegister(@ModelAttribute("user") User user) throws IOException {

		if (userService.create(user)) {
			return new ModelAndView("login").addObject("user", new User());
		}

		return new ModelAndView("register").addObject("error",
				"Username " + user.getUsername() + " is already taken.").addObject(new User());
	}
}
