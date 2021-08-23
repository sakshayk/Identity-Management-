package com.example.demo.model;

import java.io.Serializable;

public class LoginResponseModel implements Serializable {

	private String username;
	private String token;

	public LoginResponseModel(String username, String token) {
		this.username = username;
		this.token = token;
	}

	public LoginResponseModel() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
