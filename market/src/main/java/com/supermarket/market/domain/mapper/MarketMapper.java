package com.supermarket.market.domain.mapper;

import com.supermarket.market.data.model.MarketEntity;
import com.supermarket.market.domain.dto.MarketDTO;

import java.util.stream.Collectors;

public class MarketMapper {
    private ProductMapper productMapper = new ProductMapper();
    public MarketDTO toDTO(MarketEntity market){
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.setMarketName(market.getMarketName());
        marketDTO.setMarketCredits(market.getMarketCredits());
        marketDTO.setProducts(
                market.getProducts()
                        .stream()
                        .map(productMapper::toDTO)
                        .collect(Collectors.toList())
        );
        return marketDTO;
    }
    public MarketEntity toEntity(MarketDTO marketDTO){
        MarketEntity market = new MarketEntity();
        market.setMarketName(marketDTO.getMarketName());
        market.setMarketCredits(marketDTO.getMarketCredits());
        market.setProducts(
                marketDTO.getProducts()
                        .stream()
                        .map(productMapper::toEntity)
                        .collect(Collectors.toList())
        );
        return market;
    }
}
