package com.infosys.usermanagement.model;

public class AuthenticationResponse {

	private String jwt;
	
	public AuthenticationResponse() {
		
	}

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	/**
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}	
}
