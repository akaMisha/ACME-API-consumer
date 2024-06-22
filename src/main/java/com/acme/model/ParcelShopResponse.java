package com.acme.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class ParcelShopResponse.
 */
@Data
@AllArgsConstructor
public class ParcelShopResponse {
	
	/** The parcel shops. */
	private List<ParcelShop> parcelShops;
}
