package com.supermarket.market.domain.service;

import com.supermarket.market.data.model.entiity.ProductsEntity;
import com.supermarket.market.data.repositories.ProductsRepository;
import com.supermarket.market.domain.dto.ProductsDTO;
import com.supermarket.market.domain.mapper.ProductMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {
    private ProductsRepository productsRepository;
    private ProductMapper productMapper;

    public ProductServiceImpl(ProductsRepository productsRepository, ProductMapper productMapper) {
        this.productsRepository = productsRepository;
        this.productMapper = productMapper;
    }

    public ProductsDTO createProduct(ProductsDTO productsDTO){
        ProductsEntity toEntity = productMapper.toEntity(productsDTO);
        productsRepository.save(toEntity);
        return productsDTO;
    }
    public Iterable<ProductsDTO> getEveryProduct(){
        return productsRepository.findAll().stream().map(productMapper::toDTO).toList();
    }
    public ProductsDTO getProductById(Long id){
        return productsRepository.findById(id).map(productMapper::toDTO).orElse(null);
    }
    public void deleteProduct(Long id){
        productsRepository.deleteById(id);
    }
}
