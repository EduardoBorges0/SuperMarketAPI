package com.supermarket.market.domain.service;

import com.supermarket.market.data.model.MarketEntity;
import com.supermarket.market.data.repositories.MarketRepository;
import com.supermarket.market.domain.dto.MarketDTO;
import com.supermarket.market.domain.mapper.MarketMapper;
import org.springframework.stereotype.Service;

@Service
public class MarketServiceImpl {

    private MarketRepository marketRepository;
    private MarketMapper marketMapper;

    public MarketServiceImpl(MarketRepository marketRepository, MarketMapper marketMapper) {
        this.marketRepository = marketRepository;
        this.marketMapper = marketMapper;
    }

    public MarketDTO createMarket(MarketDTO marketDTO){
        MarketEntity toEntity = marketMapper.toEntity(marketDTO);
        marketRepository.save(toEntity);
        return marketDTO;
    }
}
