package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreProductService implements ProductService {

   private RestTemplate restTemplate;

   @Autowired
   public FakeStoreProductService(RestTemplate restTemplate){
       this.restTemplate=restTemplate;

   }

   private Product convertFakeSroreProductToProduct(FakeStoreProductDto fakeStoreProductDto){
       Product p=new Product();
       p.setTitle(fakeStoreProductDto.getTitle());
       p.setId(fakeStoreProductDto.getId());
       p.setCategory(new Category());
       p.getCategory().setName(fakeStoreProductDto.getCategory());
       p.setImageUrl(fakeStoreProductDto.getImage());
       p.setDescription(fakeStoreProductDto.getDescription());
       return p;
   }

   private FakeStoreProductDto convertProductToFakeStoreProduct(Product product){
       FakeStoreProductDto fakeStoreProductDto=FakeStoreProductDto.builder().
               image(product.getImageUrl())
               .title(product.getTitle())
               .price(product.getPrice())
               .id(product.getId())
               .description(product.getDescription())
               .category(product.getCategory().getName())
               .build();
       return fakeStoreProductDto;
   }

    @Override
    public Product getSingleProduct(long id) {
    FakeStoreProductDto fakeStoreProductDto=restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        return convertFakeSroreProductToProduct(fakeStoreProductDto);
    }

    @Override
    public Product addProduct(Product product) {

       FakeStoreProductDto fakeStoreProductDto=convertProductToFakeStoreProduct(product);
      FakeStoreProductDto f= restTemplate.postForObject("https://fakestoreapi.com/products",fakeStoreProductDto,FakeStoreProductDto.class);
        return convertFakeSroreProductToProduct(f);
    }

    @Override
    public void deleteProduct(long id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
    }

    @Override
    public List<Product> getAllProducts() {
      FakeStoreProductDto[] lis=restTemplate.getForObject("https://fakestoreapi.com/products",FakeStoreProductDto[].class);
      List<Product> products=new ArrayList<>();
      for(FakeStoreProductDto dto:lis){
          products.add(convertFakeSroreProductToProduct(dto));
      }
       return products;
   }

    @Override
    public Product updateProductService(long id,Product product) {
        FakeStoreProductDto fakeStoreProductDto=convertProductToFakeStoreProduct(product);
        RequestCallback requestCallback= restTemplate.httpEntityCallback(fakeStoreProductDto,FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtreactor=new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,restTemplate.getMessageConverters());
        FakeStoreProductDto response= restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PATCH,requestCallback,responseExtreactor);
        return convertFakeSroreProductToProduct(response);

    }

    @Override
    public Product replaceProduct(long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto=convertProductToFakeStoreProduct(product);
        RequestCallback requestCallback= restTemplate.httpEntityCallback(fakeStoreProductDto,FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtreactor=new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,restTemplate.getMessageConverters());
        FakeStoreProductDto response= restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT,requestCallback,responseExtreactor);
        return convertFakeSroreProductToProduct(response);

    }


}
