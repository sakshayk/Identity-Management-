package com.example.demo.model;

import java.io.Serializable;

public class LogoutRequestModel implements Serializable {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
