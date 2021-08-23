package com.example.demo.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LogoutRequestModel;
import com.example.demo.service.LogoutService;

@RestController
@RequestMapping("/logout")
public class LogoutController {

	private final LogoutService logoutService;

	LogoutController(LogoutService logoutService) {
		this.logoutService = logoutService;
	}

	@PostMapping("/token")
	public ResponseEntity logout(@RequestHeader("Authorization") String authorization) {

		String jwtToken = null;
		Optional<LogoutRequestModel> logoutResponseModel = null;
		if (authorization != null && authorization.startsWith("Bearer ")) {
			jwtToken = authorization.substring(7);
			logoutResponseModel = logoutService.logout(jwtToken);
		}

		return logoutResponseModel.isPresent() ? ResponseEntity.ok(logoutResponseModel)
				: ResponseEntity.notFound().build();
	}

	private boolean logoutRequestModel(LogoutRequestModel logoutRequestModel) {
		if (Optional.ofNullable(logoutRequestModel).isPresent()
				&& !"".equalsIgnoreCase(logoutRequestModel.getToken())) {
			return true;
		}
		return false;
	}
}
