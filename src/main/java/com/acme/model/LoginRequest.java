package com.acme.model;

import lombok.AllArgsConstructor;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginRequest.
 */
@Data
@AllArgsConstructor
public class LoginRequest {
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The omit claims. */
	private String omitClaims;
}
