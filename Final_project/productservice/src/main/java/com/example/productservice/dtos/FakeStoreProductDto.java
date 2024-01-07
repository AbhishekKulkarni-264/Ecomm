package com.example.productservice.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FakeStoreProductDto {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
