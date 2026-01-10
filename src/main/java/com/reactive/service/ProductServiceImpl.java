package com.reactive.service;

import com.reactive.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl  implements ProductService{

    private static List<Product> products = new ArrayList<>(
            new Product(1022, "milk", "alimentation", 1.400,234),
            new Product(1023, "milk", "alimentation", 1.400, 500),
            new Product(1024, "tail", "home", 400, 1200),
            new Product(1025, "boot", "alimentation", 900, 456),
            new Product(1026, "hegs", "alimentation", 1900, 10000),
            new Product(1027, "television", "home", 13.4000, 49),
            new Product(1028, "iron", "home", 11.400, 458)
    );
    
    @Override
    public Flux<Product> catalog() {
        return null;
    }

    @Override
    public Flux<Product> productCategory(String category) {
        return null;
    }

    @Override
    public Mono<Product> productCode(int code) {
        return null;
    }

    @Override
    public Mono<Void> saveProduct(Product product) {
        return null;
    }

    @Override
    public Mono<Product> deleteProduct(int code) {
        return null;
    }

    @Override
    public Mono<Product> updateProduct(int code, double price) {
        return null;
    }
}
