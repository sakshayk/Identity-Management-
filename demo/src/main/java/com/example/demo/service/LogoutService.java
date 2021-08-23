package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LogoutRequestModel;
import com.example.demo.repo.UserRepositoryRC;
import com.example.demo.repo.model.UserRC;

@Service
public class LogoutService {

	@Autowired
	private UserRepositoryRC userRepositoryRC;

	public Optional<LogoutRequestModel> logout(String token) {

		UserRC userRC = userRepositoryRC.findByToken(token);
		LogoutRequestModel logoutResponseModel = new LogoutRequestModel();
		;
		if (Optional.ofNullable(userRC).isPresent()) {
			userRepositoryRC.delete(token);
			logoutResponseModel.setToken("LOGGED OUT");
		} else {
			logoutResponseModel.setToken("USER NOT FOUND");
		}

		return Optional.ofNullable(logoutResponseModel);

	}

}
