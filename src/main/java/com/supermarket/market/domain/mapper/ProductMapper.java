package com.supermarket.market.domain.mapper;

import com.supermarket.market.data.model.entiity.MarketEntity;
import com.supermarket.market.data.model.entiity.ProductsEntity;
import com.supermarket.market.domain.dto.market.MarketSimpleDTO;
import com.supermarket.market.domain.dto.ProductsDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


    private final MarketMapper marketMapper;
    private MarketSimpleDTO markerSimpleDTO;

    public ProductMapper(@Lazy MarketMapper marketMapper) {
        this.marketMapper = marketMapper;
    }

    public ProductsDTO toDTO(ProductsEntity products){
        ProductsDTO productsDTO = new ProductsDTO();
        productsDTO.setId(products.getId());
        productsDTO.setProduct_name(products.getProduct_name());
        productsDTO.setCategory(products.getCategory());
        productsDTO.setValue(products.getValue());
        productsDTO.setStock(products.getStock());

        MarketSimpleDTO marketSimpleDTO = new MarketSimpleDTO();
        marketSimpleDTO.setMarketId(products.getMarket().getMarketId());
        marketSimpleDTO.setMarketName(products.getMarket().getMarketName());
        productsDTO.setMarket(marketSimpleDTO);

        return productsDTO;
    }


    public ProductsEntity toEntity(ProductsDTO productsDTO){
        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setId(productsDTO.getId());
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

