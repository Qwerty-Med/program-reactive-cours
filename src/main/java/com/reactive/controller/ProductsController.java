package com.reactive.controller;

import com.reactive.model.Product;
import com.reactive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductsController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "products")
    public ResponseEntity<Flux<Product>>  products(){
        return new ResponseEntity<>( productService.catalog(),HttpStatus.OK);
    }

    @GetMapping(value = "products/for-category")
    public ResponseEntity<Flux<Product>> productCategory(@RequestParam String category){
        return new ResponseEntity<>( productService.productCategory(category),HttpStatus.OK);
    }

    @GetMapping(value = "products/{code}")
    public ResponseEntity<Mono<Product>> productCategory(@PathVariable int code){
        return new ResponseEntity<>( productService.productCode(code),HttpStatus.OK);
    }


    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Void>>  saveProduct(@RequestParam Product product){
        return new ResponseEntity<>( productService.saveProduct(product),HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{code}")
    public Mono<ResponseEntity<Product>> deleteProduct(@PathVariable int code){
        return productService.deleteProduct(code)
                .map(p->new ResponseEntity<>(p, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PutMapping(value = "update/{code}")
    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable int code, @RequestParam("price") Double price){
        return productService.updatePrice(code, price)
                .map(p->new ResponseEntity<>(p, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

}
