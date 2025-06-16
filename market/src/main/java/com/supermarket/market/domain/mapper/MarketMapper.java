package com.supermarket.market.domain.mapper;

import com.supermarket.market.data.model.entiity.MarketEntity;
import com.supermarket.market.domain.dto.market.MarketDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MarketMapper {
    private final ProductMapper productMapper;

    public MarketMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
    public MarketDTO toDTO(MarketEntity market){
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.setMarketId(market.getMarketId()); // <- Certifique-se de incluir essa linha
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
