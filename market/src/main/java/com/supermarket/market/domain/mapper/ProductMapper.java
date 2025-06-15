package com.supermarket.market.domain.mapper;

import com.supermarket.market.data.model.MarketEntity;
import com.supermarket.market.data.model.ProductsEntity;
import com.supermarket.market.domain.dto.MarketDTO;
import com.supermarket.market.domain.dto.ProductsDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


    private final MarketMapper marketMapper;

    public ProductMapper(@Lazy MarketMapper marketMapper) {
        this.marketMapper = marketMapper;
    }

    public ProductsDTO toDTO(ProductsEntity products){
        ProductsDTO productsDTO = new ProductsDTO();
        productsDTO.setProduct_name(products.getProduct_name());
        productsDTO.setCategory(products.getCategory());
        productsDTO.setValue(products.getValue());
        productsDTO.setStock(products.getStock());
        productsDTO.setMarket(marketMapper.toDTO(products.getMarket()));

        return productsDTO;
    }

    public ProductsEntity toEntity(ProductsDTO productsDTO){
        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setProduct_name(productsDTO.getProduct_name());
        productsEntity.setCategory(productsDTO.getCategory());
        productsEntity.setValue(productsDTO.getValue());
        productsEntity.setStock(productsDTO.getStock());
        MarketEntity marketEntity = new MarketEntity();
        marketEntity.setMarketId(productsDTO.getMarket().getMarketId());
        productsEntity.setMarket(marketEntity);
        return productsEntity;
    }
}

