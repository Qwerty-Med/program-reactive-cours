package com.reactive.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    private int codProduct;
    private String name;
    private String category;
    private double priceUnitary;
    private int stock;

}
