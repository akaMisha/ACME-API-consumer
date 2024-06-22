package com.acme.controller;

import com.acme.model.LoginResponse;
import com.acme.model.ParcelShopResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ParcelShopAPI.
 */
public interface ParcelShopAPI {

	/**
	 * Login.
	 *
	 * @return the login response
	 */
	public LoginResponse login();

	/**
	 * Gets the parcel shops.
	 *
	 * @param token the token
	 * @return the parcel shops
	 */
	public ParcelShopResponse getParcelShops(String token);
}
