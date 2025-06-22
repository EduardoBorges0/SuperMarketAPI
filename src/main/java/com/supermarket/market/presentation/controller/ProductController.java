package com.supermarket.market.presentation.controller;

import com.supermarket.market.data.model.response.MessageError;
import com.supermarket.market.domain.dto.ProductsDTO;
import com.supermarket.market.domain.service.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@RequestBody ProductsDTO productsDTO) {
        return productService.createProduct(productsDTO);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/getEveryProduct")
    public ResponseEntity<?> getEveryProduct() {
        return productService.getEveryProduct();
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
