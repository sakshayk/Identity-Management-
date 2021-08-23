package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.jpa.entity.UserDAO;

@Service
public class RegisterService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public Optional<UserDAO> register(String username, String password) {
		UserDAO newUser = new UserDAO();
		newUser.setUsername(username);
		newUser.setPassword(bcryptEncoder.encode(password));
		return Optional.of(userDao.save(newUser));

	}

}
