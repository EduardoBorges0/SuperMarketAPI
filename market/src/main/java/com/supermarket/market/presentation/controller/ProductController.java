package com.supermarket.market.presentation.controller;

import com.supermarket.market.domain.dto.ProductsDTO;
import com.supermarket.market.domain.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getProductById/{id}")
    public ProductsDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @GetMapping("/getEveryProduct")
    public Iterable<ProductsDTO> getEveryProduct(){
        return productService.getEveryProduct();
    }
    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
