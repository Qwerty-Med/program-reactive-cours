package com.reactive.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    private int codProduct;
    private String name;
    private String category;
    private Double priceUnitary;
    private int stock;

}
