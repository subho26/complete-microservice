package com.product;

import com.product.model.Product;
import com.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class ProductServiceApplication {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void initProducts() {
        List<Product> products = Arrays.asList(
                new Product(101, "product1", "10-12-2021", 150.0),
                new Product(102, "product2", "03-9-2022", 290.0),
                new Product(103, "product3", "14-01-2022", 254.0));
        productRepository.saveAll(products);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
