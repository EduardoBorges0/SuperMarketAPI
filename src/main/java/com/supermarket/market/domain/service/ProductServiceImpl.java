package com.supermarket.market.domain.service;

import com.supermarket.market.data.model.entiity.ProductsEntity;
import com.supermarket.market.data.model.response.MessageError;
import com.supermarket.market.data.repositories.ProductsRepository;
import com.supermarket.market.domain.dto.ProductsDTO;
import com.supermarket.market.domain.mapper.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {
    private ProductsRepository productsRepository;
    private ProductMapper productMapper;


    public ProductServiceImpl(ProductsRepository productsRepository, ProductMapper productMapper) {
        this.productsRepository = productsRepository;
        this.productMapper = productMapper;
    }

    public ResponseEntity<?> createProduct(ProductsDTO productsDTO) {
        ProductsEntity toEntity = productMapper.toEntity(productsDTO);
        try {
            boolean isError = productsDTO.getProduct_name() == null
                    || productsDTO.getCategory() == null
                    || productsDTO.getValue() <= 0.0
                    || productsDTO.getStock() <= 0;
            if(isError){
                MessageError messageError = new MessageError();
                messageError.setMessage("Fill every field!!");
                messageError.setCode(400);
                return ResponseEntity.status(messageError.code).body(messageError);
            }else{
                return ResponseEntity.status(HttpStatus.CREATED).body(productsRepository.save(toEntity));
            }
        } catch (Exception e) {
            MessageError messageError = new MessageError();
            messageError.setMessage("Internal server error: " + e.getMessage() + "");
            messageError.setCode(500);
            return ResponseEntity.status(messageError.code).body(messageError);
        }
    }

    public ResponseEntity<?> getEveryProduct() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productsRepository.findAll().stream().map(productMapper::toDTO).toList());
        }catch (Exception e){
            MessageError messageError = new MessageError();
            messageError.setMessage("Internal server error: " + e.getMessage() + "");
            messageError.setCode(500);
            return ResponseEntity.status(messageError.code).body(messageError);
        }
    }
    public ProductsEntity findEntityById(Long id) {
        return productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public ResponseEntity<?> getProductById(Long id) {
        try{
            boolean isError = id == null || !productsRepository.existsById(id);
            if(isError){
                MessageError messageError = new MessageError();
                messageError.setMessage("Id not exist!!");
                messageError.setCode(404);
                return ResponseEntity.status(messageError.code).body(messageError);
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(productsRepository.findById(id).map(productMapper::toDTO).orElse(null));
            }
        }catch (Exception e){
            MessageError messageError = new MessageError();
            messageError.setMessage("Internal server error: " + e.getMessage() + "");
            messageError.setCode(500);
            return ResponseEntity.status(messageError.code).body(messageError);
        }
    }

    public void deleteProduct(Long id) {
        productsRepository.deleteById(id);
    }
    public void stockValueUpdate(Long id, Integer stock, Integer quantity){
        ProductsEntity product = findEntityById(id);
        product.setStock(stock - quantity);
        productsRepository.save(product);
    }
}
