package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.jpa.entity.UserDAO;
import com.example.demo.repo.UserRepositoryRC;
import com.example.demo.repo.model.UserRC;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepositoryRC userRepositoryRC;

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDAO user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public UserDetails findByToken(String token) throws UsernameNotFoundException {
		UserRC user = userRepositoryRC.findByToken(token);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with token: " + token);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
}
