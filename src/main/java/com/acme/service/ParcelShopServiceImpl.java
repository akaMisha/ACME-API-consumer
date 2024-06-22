package com.acme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.controller.ParcelShopAPI;
import com.acme.model.LoginResponse;
import com.acme.model.ParcelShopResponse;
import com.acme.repo.ParcelShopRepo;

// TODO: Auto-generated Javadoc
/**
 * The Class ParcelShopService.
 */
@Service
public class ParcelShopServiceImpl implements ParcelShopService {

	/** The parcel shop API. */
	private final ParcelShopAPI parcelShopAPI;

	/** The parcel shop repo. */
	private final ParcelShopRepo parcelShopRepo;

	/**
	 * Instantiates a new parcel shop service.
	 *
	 * @param parcelShopAPIImpl the parcel shop API impl
	 * @param parcelShopRepo    the parcel shop repo
	 */
	@Autowired
	public ParcelShopServiceImpl(ParcelShopAPI parcelShopAPIImpl, ParcelShopRepo parcelShopRepo) {
		this.parcelShopAPI = parcelShopAPIImpl;
		this.parcelShopRepo = parcelShopRepo;
	}

	/**
	 * Fetch and save parcel shops.
	 */
	@Override
	public void fetchAndSaveParcelShops() {
		LoginResponse loginResponse = parcelShopAPI.login();

		if (loginResponse.getAccessToken() != null) {
			ParcelShopResponse parcelShopResponse = parcelShopAPI.getParcelShops(loginResponse.getAccessToken());
			if (parcelShopResponse != null) {
				parcelShopRepo.saveAll(parcelShopResponse.getParcelShops());
			} else {
				throw new Error("Invalid access token.");
			}
		} else
			throw new Error("Null access token.");

	}

}
