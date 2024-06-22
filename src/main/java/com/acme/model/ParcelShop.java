package com.acme.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class ParcelShop.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParcelShop {

	/** The id. */
	private String id;

	/** The name. */
	private String name;

	/** The type. */
	private String type;

	/** The latitude. */
	private Double latitude;

	/** The longitude. */
	private Double longitude;

	/** The carrier. */
	private String carrier;

	/** The address line 1. */
	private String addressLine1;

	/** The post code. */
	private String postCode;

	/** The city. */
	private String city;

	/** The country. */
	private String country;

	/** The opening times. */
	private List<OpeningTime> openingTimes;

	/** The carrier data. */
	private String carrierData;

}
