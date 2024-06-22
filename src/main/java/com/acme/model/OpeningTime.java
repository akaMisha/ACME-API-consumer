package com.acme.model;

import lombok.AllArgsConstructor;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class OpeningTime.
 */
@Data
@AllArgsConstructor
public class OpeningTime {

	/** The day. */
	private Integer day;

	/** The from. */
	private String from;

	/** The to. */
	private String to;

	/** The closed during. */
	private String closedDuring;

}
