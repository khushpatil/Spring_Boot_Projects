package com.productservice_microservices.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.productservice_microservices.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
