package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.model.LoginResponseModel;
import com.example.demo.repo.UserRepositoryRC;
import com.example.demo.repo.model.UserRC;

@Service
public class LoginService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepositoryRC userRepositoryRC;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	public Optional<LoginResponseModel> loginViaCreds(String username, String password) throws Exception {
		authenticate(username, password);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		final String token = jwtTokenUtil.generateToken(userDetails);

		LoginResponseModel loginResponseModel = new LoginResponseModel();
		loginResponseModel.setUsername(username);
		loginResponseModel.setToken(token);

		UserRC userRC = new UserRC(username, password, token);
		userRepositoryRC.save(userRC);
		userRepositoryRC.findByToken(userRC.getToken());
		return Optional.ofNullable(loginResponseModel);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	public Optional<LoginResponseModel> loginViaToken(String token) {

		UserRC userRC = userRepositoryRC.findByToken(token);
		LoginResponseModel loginResponseModel = null;
		if (Optional.ofNullable(userRC).isPresent()) {
			loginResponseModel = new LoginResponseModel();
			loginResponseModel.setUsername(userRC.getUsername());
			loginResponseModel.setToken(userRC.getToken());
		}

		return Optional.ofNullable(loginResponseModel);
	}

}
