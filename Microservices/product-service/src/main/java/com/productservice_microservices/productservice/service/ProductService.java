package com.productservice_microservices.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productservice_microservices.productservice.dto.ProductRequest;
import com.productservice_microservices.productservice.dto.ProductResponse;
import com.productservice_microservices.productservice.model.Product;
import com.productservice_microservices.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository prodrepo;

    public void createProduct(ProductRequest prodrequest) {
        Product product = Product.builder()
                .name(prodrequest.getName())
                .desc(prodrequest.getDesc())
                .price(prodrequest.getPrice())
                .build();

        prodrepo.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = prodrepo.findAll();

        return products.stream().map(this::maptoProductResponse).toList();
    }

    private ProductResponse maptoProductResponse(Product prod) {
        return ProductResponse.builder()
                .id(prod.getId())
                .name(prod.getName())
                .desc(prod.getDesc())
                .price(prod.getPrice())
                .build();
    }
}
