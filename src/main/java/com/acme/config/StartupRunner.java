package com.acme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.acme.service.ParcelShopService;

// TODO: Auto-generated Javadoc
/**
 * The Class StartupRunner.
 */
@Component
@Configuration
public class StartupRunner {

	/** The parcel shop service. */
	private final ParcelShopService parcelShopService;

	/**
	 * Instantiates a new startup runner.
	 *
	 * @param parcelShopServiceImpl the parcel shop service impl
	 */
	@Autowired
	public StartupRunner(ParcelShopService parcelShopServiceImpl) {
		this.parcelShopService = parcelShopServiceImpl;
	}

	/**
	 * Runner.
	 *
	 * @return the application runner
	 */
	@Bean
	public ApplicationRunner runner() {
		return args -> parcelShopService.fetchAndSaveParcelShops();
	}

}
