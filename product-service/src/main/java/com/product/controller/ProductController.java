package com.product.controller;

import com.product.model.Product;
import com.product.service.ProductService;
import com.product.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> placeOrder() {
        if (jwtUtil.getClaims().get("role").equals("user")) {
            System.out.println("999999====" + 8086);
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.UNAUTHORIZED);
        }
    }
}
