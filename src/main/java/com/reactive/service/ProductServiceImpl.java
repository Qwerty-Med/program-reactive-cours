package com.reactive.service;

import com.reactive.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.xml.catalog.Catalog;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl  implements ProductService{

    private static List<Product> products = new ArrayList<>( List.of(       new Product(1022, "milk", "alimentation", 1.400,234),
            new Product(1023, "milk", "alimentation", 1.400, 500),
            new Product(1024, "tail", "home", 9099.0, 20),
            new Product(1025, "boot", "alimentation", 900.0, 456),
            new Product(1026, "hegs", "alimentation", 1900.0, 10000),
            new Product(1027, "television", "home", 13.4000, 49),
            new Product(1028, "iron", "home", 11.400, 458))
    );
    
    @Override
    public Flux<Product> catalog() {

        return  Flux.fromIterable(products).delayElements(Duration.ofSeconds(2));
    }

    @Override
    public Flux<Product> productCategory(String category) {
        return  catalog().filter(product -> product.getCategory().equals(category));
    }

    @Override
    public Mono<Product> productCode(int code) {
        return catalog()//FLUX<Product>
                      .filter(product -> product.getCodProduct() == code) //FLUX<Product>
                         .next();//Mono<Product>
                         //.swithcIfEmpty(Mono.just(new Product()));
    }

    @Override
    public Mono<Void> saveProduct(Product product) {
        return productCode(product.getCodProduct())//Mono<Produc>
                .switchIfEmpty(Mono.just(product).map(p->{
                    products.add(product);
                    return p;
                }))//Mono<Produc>
                .then();//Mono<Void>
    }

    @Override
    public Mono<Product> deleteProduct(int code) {
        return productCode(code) //Mono<Product>
                   .map(product -> {
                       products.removeIf(P->P.getCodProduct()==code);
                       return product;
                   });//Mono<Producto>
    }

    @Override
    public Mono<Product> updatePrice(int code, double price) {
       return  productCode(code)
               .map(p->{
                   p.setPriceUnitary(price);
                   return p;
               });
    }


}
