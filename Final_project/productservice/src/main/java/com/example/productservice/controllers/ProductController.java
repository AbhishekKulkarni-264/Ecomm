package com.example.productservice.controllers;


import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

     private final ProductService productService;

     @Autowired
     public ProductController(ProductService productService){
         this.productService=productService;
     }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){

      return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable long id){
        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product){
      return productService.addProduct(product);
    }

    @PatchMapping("/{id}")
    public  Product updateProduct(@PathVariable long id,@RequestBody Product product){

         return productService.updateProductService(id,product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable long id, @RequestBody Product product){
         return new ResponseEntity<>(productService.replaceProduct(id,product),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
     return new ResponseEntity<>(HttpStatus.OK);
    }
}
