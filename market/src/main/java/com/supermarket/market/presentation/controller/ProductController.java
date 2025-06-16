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
        try {
            boolean isError = Objects.equals(productsDTO.getProduct_name(), "")
                    || Objects.equals(productsDTO.getCategory(), "") ||
                    productsDTO.getValue() == 0 ||
                    productsDTO.getStock() == 0;
            if(isError){
                MessageError messageError = new MessageError();
                messageError.setMessage("Fill every field!!");
                messageError.setCode(400);
                return ResponseEntity.status(messageError.code).body(messageError);
            }else{
                return ResponseEntity.status(200).body(productService.createProduct(productsDTO));
            }
        } catch (Exception e) {
            MessageError messageError = new MessageError();
            messageError.setMessage("Internal server error: " + e.getMessage());
            messageError.setCode(500);
            return ResponseEntity.status(messageError.code).body(messageError);
        }
    }

    @GetMapping("/getProductById/{id}")
    public ProductsDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/getEveryProduct")
    public Iterable<ProductsDTO> getEveryProduct() {
        return productService.getEveryProduct();
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
