package com.acme.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.acme.model.ParcelShop;

/**
 * The Interface ParcelShopRepo.
 */
public interface ParcelShopRepo extends MongoRepository<ParcelShop, String>{

}
