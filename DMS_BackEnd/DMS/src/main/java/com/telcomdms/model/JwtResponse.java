package com.telcomdms.model;

public class JwtResponse {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtResponse() {
	}

	public JwtResponse(String token) {
		super();
		this.token = token;
	}
	
	
}
