package com.example.productservice.services;

import com.example.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(long id);
    Product addProduct(Product product);
    void deleteProduct(long id);
    List<Product> getAllProducts();
    Product updateProductService(long id,Product product);

    Product replaceProduct(long id,Product product);

}
