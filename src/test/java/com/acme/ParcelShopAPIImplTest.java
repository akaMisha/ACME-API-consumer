package com.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.acme.config.APIProperties;
import com.acme.controller.ParcelShopAPIImpl;
import com.acme.model.LoginRequest;
import com.acme.model.LoginResponse;
import com.acme.model.ParcelShop;
import com.acme.model.ParcelShopResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ParcelShopAPIImplTest.
 */
public class ParcelShopAPIImplTest {

	/** The mock API properties. */
	@Mock
	private APIProperties mockAPIProperties;

	/** The mock rest template. */
	@Mock
	private RestTemplate mockRestTemplate;

	/** The parcel shop API. */
	private ParcelShopAPIImpl parcelShopAPI;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		parcelShopAPI = new ParcelShopAPIImpl(mockAPIProperties, mockRestTemplate);
	}

	/**
	 * Test login.
	 */
	@Test
	void testLogin() {
		when(mockAPIProperties.getBaseUrl()).thenReturn("http://example.com");
		when(mockAPIProperties.getClientId()).thenReturn("testClientId");

		String loginUrl = "http://example.com/auth/login";
		LoginRequest expectedRequest = new LoginRequest("assignment-test@ubsend.com", "p0DrmE)E+BQH$]KasMSb", "");
		HttpHeaders expectedHeaders = new HttpHeaders();
		expectedHeaders.set("ClientId", "testClientId");
		HttpEntity<LoginRequest> expectedEntity = new HttpEntity<>(expectedRequest, expectedHeaders);
		LoginResponse mockLoginResponse = new LoginResponse("mocked_token", null, null, null, null, "denis", null, null,
				null);
		mockLoginResponse.setAccessToken("mocked_token");
		ResponseEntity<LoginResponse> mockResponseEntity = new ResponseEntity<>(mockLoginResponse, HttpStatus.OK);
		when(mockRestTemplate.exchange(loginUrl, HttpMethod.POST, expectedEntity, LoginResponse.class))
				.thenReturn(mockResponseEntity);

		LoginResponse actualResponse = parcelShopAPI.login();

		verify(mockRestTemplate, times(1)).exchange(eq(loginUrl), eq(HttpMethod.POST), any(HttpEntity.class),
				eq(LoginResponse.class));
		verify(mockAPIProperties, times(1)).getBaseUrl();
		verify(mockAPIProperties, times(1)).getClientId();
		assertEquals("mocked_token", actualResponse.getAccessToken());
	}

	/**
	 * Test get parcel shops.
	 */
	@Test
	void testGetParcelShops() {
		when(mockAPIProperties.getBaseUrl()).thenReturn("http://example.com");
		when(mockAPIProperties.getClientId()).thenReturn("testClientId");
		when(mockAPIProperties.getCarrier()).thenReturn("testCarrier");
		when(mockAPIProperties.getCountry()).thenReturn("testCountry");
		when(mockAPIProperties.getLimit()).thenReturn(10);

		String token = "mocked_token";
		String expectedUrl = "http://example.com/parcel-shops/parcel-shops?radius=100000&carrier=testCarrier&country=testCountry&limit=10";
		HttpHeaders expectedHeaders = new HttpHeaders();
		expectedHeaders.set("Authorization", "Bearer " + token);
		expectedHeaders.set("ClientId", "testClientId");
		HttpEntity<?> expectedEntity = new HttpEntity<>(expectedHeaders);
		List<ParcelShop> mockedParcelShops = Collections.singletonList(new ParcelShop());
		ResponseEntity<List<ParcelShop>> mockResponseEntity = new ResponseEntity<>(mockedParcelShops, HttpStatus.OK);
		when(mockRestTemplate.exchange(expectedUrl, HttpMethod.GET, expectedEntity,
				new ParameterizedTypeReference<List<ParcelShop>>() {
				})).thenReturn(mockResponseEntity);

		ParcelShopResponse actualResponse = parcelShopAPI.getParcelShops(token);

		verify(mockRestTemplate, times(1)).exchange(eq(expectedUrl), eq(HttpMethod.GET), any(HttpEntity.class),
				eq(new ParameterizedTypeReference<List<ParcelShop>>() {
				}));
		verify(mockAPIProperties, times(1)).getBaseUrl();
		verify(mockAPIProperties, times(1)).getClientId();
		verify(mockAPIProperties, times(1)).getCarrier();
		verify(mockAPIProperties, times(1)).getCountry();
		verify(mockAPIProperties, times(1)).getLimit();
		assertEquals(mockedParcelShops, actualResponse.getParcelShops());
	}
}
