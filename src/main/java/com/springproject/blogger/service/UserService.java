package com.springproject.blogger.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springproject.blogger.dao.AuthorityDao;
import com.springproject.blogger.dao.UserDao;
import com.springproject.blogger.model.Authority;
import com.springproject.blogger.model.User;
import com.springproject.blogger.security.CustomUserDetails;
import com.springproject.blogger.settings.AuthorityType;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthorityDao authorityDao;

	BCryptPasswordEncoder encoder;

	public UserService() {

		super();
		encoder = new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		return new CustomUserDetails(user);
	}

	private boolean created;

	public boolean create(User user) {

		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(authorityDao.getByAuthority(AuthorityType.ROLE_USER));

		user.setAuthorities(authorities);
		user.setPassword(encoder.encode(user.getPassword()));

		created = false;
		userDao.findByUsernameOptional(user.getUsername()).orElseGet(() -> {
			created = true;
			return userDao.save(user);
		});

		return created;
	}

	public User save(User user) {
		return userDao.save(user);
	}

	public Optional<User> retrieveById(long id) {
		return userDao.findById(id);
	}

}
