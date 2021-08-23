package com.example.demo.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.entity.UserDAO;
import com.example.demo.model.UserDTO;
import com.example.demo.service.RegisterService;

@RestController
@CrossOrigin
@RequestMapping("/signup")
public class SignUpController {

	private final RegisterService registerService;

	public SignUpController(RegisterService registerService) {
		this.registerService = registerService;
	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody UserDTO userDTO) {
		if (!registerModelValid(userDTO)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		Optional<UserDAO> registerResponseModel = registerService.register(userDTO.getUsername(),
				userDTO.getPassword());

		return registerResponseModel.isPresent() ? ResponseEntity.ok(registerResponseModel)
				: ResponseEntity.notFound().build();
	}

	private boolean registerModelValid(UserDTO userDTO) {
		if (Optional.ofNullable(userDTO).isPresent()) {
			if (Optional.ofNullable(userDTO.getUsername()).isPresent() && Optional.ofNullable(userDTO.getPassword()).isPresent()) {
				return true;
			}
		}
		return false;
	}

}