package com.reactive.service;

import com.reactive.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> catalog();
    Flux<Product> productCategory(String category);
    Mono<Product> productCode(int code);
    Mono<Void> saveProduct(Product product);
    Mono<Product> deleteProduct(int code);
    Mono<Product> updateProduct(int code, double price);

}
