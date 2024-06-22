package com.acme;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acme.controller.ParcelShopAPI;
import com.acme.model.LoginResponse;
import com.acme.model.ParcelShopResponse;
import com.acme.repo.ParcelShopRepo;
import com.acme.service.ParcelShopServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class ParcelShopServiceImplTest.
 */
public class ParcelShopServiceImplTest {

	/** The mock parcel shop API. */
	@Mock
	private ParcelShopAPI mockParcelShopAPI;

	/** The mock parcel shop repo. */
	@Mock
	private ParcelShopRepo mockParcelShopRepo;

	/** The parcel shop service. */
	private ParcelShopServiceImpl parcelShopService;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		parcelShopService = new ParcelShopServiceImpl(mockParcelShopAPI, mockParcelShopRepo);
	}

	/**
	 * Test fetch and save parcel shops.
	 */
	@Test
	void testFetchAndSaveParcelShops() {
		LoginResponse loginResponse = new LoginResponse("mocked_token", null, null, null, null, "denis", null, null,
				null);
		loginResponse.setAccessToken("denis");
		when(mockParcelShopAPI.login()).thenReturn(loginResponse);

		ParcelShopResponse parcelShopResponse = new ParcelShopResponse(Collections.emptyList());
		when(mockParcelShopAPI.getParcelShops(anyString())).thenReturn(parcelShopResponse);

		parcelShopService.fetchAndSaveParcelShops();

		verify(mockParcelShopAPI, times(1)).login();
		verify(mockParcelShopAPI, times(1)).getParcelShops(anyString());
		verify(mockParcelShopRepo, times(1)).saveAll(anyList());
	}
}