package com.acme.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.acme.config.APIProperties;
import com.acme.model.LoginRequest;
import com.acme.model.LoginResponse;
import com.acme.model.ParcelShop;
import com.acme.model.ParcelShopResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ParcelShopAPI.
 */
@Component
public class ParcelShopAPIImpl implements ParcelShopAPI {

	/** The api properties. */
	private final APIProperties apiProperties;

	/** The rest template. */
	private final RestTemplate restTemplate;

	/**
	 * Instantiates a new parcel shop API.
	 *
	 * @param apiProperties the api properties
	 * @param restTemplate  the rest template
	 */
	@Autowired
	public ParcelShopAPIImpl(APIProperties apiProperties, RestTemplate restTemplate) {
		this.apiProperties = apiProperties;
		this.restTemplate = restTemplate;
	}

	/**
	 * Login.
	 *
	 * @return the login response
	 */
	@Override
	public LoginResponse login() {
		String url = apiProperties.getBaseUrl() + "/auth/login";
		LoginRequest request = new LoginRequest("assignment-test@ubsend.com", "p0DrmE)E+BQH$]KasMSb", "");
		HttpHeaders headers = new HttpHeaders();
		headers.set("ClientId", apiProperties.getClientId());

		HttpEntity<LoginRequest> entity = new HttpEntity<>(request, headers);

		ResponseEntity<LoginResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity,
				LoginResponse.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {
			throw new RuntimeException("Failed to login: " + response.getStatusCode());
		}
	}

	/**
	 * Gets the parcel shops.
	 *
	 * @param token the token
	 * @return the parcel shops
	 */
	@Override
	public ParcelShopResponse getParcelShops(String token) {

		String url = apiProperties.getBaseUrl() + "/parcel-shops/parcel-shops?radius=100000";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		headers.set("ClientId", apiProperties.getClientId());
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("carrier", apiProperties.getCarrier()).queryParam("country", apiProperties.getCountry())
				.queryParam("limit", apiProperties.getLimit());
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<List<ParcelShop>> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
				entity, new ParameterizedTypeReference<List<ParcelShop>>() {
				});

		if (response.getStatusCode() == HttpStatus.OK) {
			List<ParcelShop> parcelShops = response.getBody();
			return new ParcelShopResponse(parcelShops != null ? parcelShops : Collections.emptyList());
		} else {
			throw new RuntimeException("Failed to get parcel shops: " + response.getStatusCode());
		}
	}
}
