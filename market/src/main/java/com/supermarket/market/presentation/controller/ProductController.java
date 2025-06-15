package com.supermarket.market.presentation.controller;

import com.supermarket.market.domain.dto.ProductsDTO;
import com.supermarket.market.domain.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductServiceImpl productService;
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }
    @PostMapping("/createProduct")
    public ProductsDTO createProduct(@RequestBody ProductsDTO productsDTO){
        return productService.createProduct(productsDTO);
    }
}
