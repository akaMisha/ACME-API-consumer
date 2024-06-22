package com.acme;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.acme.model.ParcelShop;
import com.acme.repo.ParcelShopRepo;

// TODO: Auto-generated Javadoc
/**
 * The Class ParcelShopRepoTest.
 */
@DataMongoTest
public class ParcelShopRepoTest {

	/** The parcel shop repo. */
	@Autowired
	private ParcelShopRepo parcelShopRepo;

	/**
	 * Test find by name.
	 */
	@Test
	public void testFindByName() {

		ParcelShop parcelShop = new ParcelShop("shop1", "address1", "city1", null, null, null, null, null, null, null,
				null, null);
		parcelShopRepo.save(parcelShop);

		List<ParcelShop> shops = parcelShopRepo.findAll();

		assertThat(shops).hasSize(11);
		assertThat(shops.get(10).getName()).isEqualTo("address1");
	}
}
