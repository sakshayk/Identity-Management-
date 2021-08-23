package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LoginRequestModel;
import com.example.demo.model.LoginResponseModel;
import com.example.demo.service.LoginService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/viaCreds")
	public ResponseEntity loginViaCreds(@RequestBody LoginRequestModel loginRequestModel, HttpServletResponse response)
			throws Exception {
		if (!loginRequestModelValid(loginRequestModel)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		Optional<LoginResponseModel> loginResponseModel = loginService.loginViaCreds(loginRequestModel.getUsername(),
				loginRequestModel.getPassword());

		if (loginResponseModel.isPresent()) {
			response.addCookie(new Cookie("Authorization", loginResponseModel.get().getToken()));
			return ResponseEntity.ok(loginResponseModel);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/viaToken")
	public ResponseEntity loginViaToken(@RequestBody LoginRequestModel loginRequestModel) {
		if (!loginRequestModelValid(loginRequestModel)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		Optional<LoginResponseModel> loginResponseModel = loginService.loginViaToken(loginRequestModel.getToken());
		return loginResponseModel.isPresent() ? ResponseEntity.ok(loginResponseModel)
				: ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

	private boolean loginRequestModelValid(LoginRequestModel loginRequestModel) {
		if (Optional.of(loginRequestModel).isPresent()) {
			if (Optional.ofNullable(loginRequestModel.getUsername()).isPresent()
					&& Optional.ofNullable(loginRequestModel.getPassword()).isPresent()) {
				return true;
			}
			if (Optional.ofNullable(loginRequestModel.getToken()).isPresent()) {
				return true;
			}
		}
		return false;
	}
}