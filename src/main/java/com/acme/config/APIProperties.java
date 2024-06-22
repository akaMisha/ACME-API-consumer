package com.acme.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class APIProperties.
 */
@Configuration
@ConfigurationProperties(prefix = "api")
@Data
public class APIProperties {
	
	/** The base url. */
	private String baseUrl;
	
	/** The limit. */
	private int limit;
	
	/** The carrier. */
	private String carrier;
	
	/** The country. */
	private String country;
	
	/** The client id. */
	private String clientId;
}
