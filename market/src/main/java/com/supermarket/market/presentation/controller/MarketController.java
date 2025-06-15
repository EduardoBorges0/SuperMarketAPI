package com.supermarket.market.presentation.controller;

import com.supermarket.market.domain.dto.MarketDTO;
import com.supermarket.market.domain.service.MarketServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market")
public class MarketController {
    private MarketServiceImpl marketService;

    public MarketController(MarketServiceImpl marketService) {
        this.marketService = marketService;
    }

    @PostMapping("/createMarket")
    public MarketDTO createMarket(@RequestBody MarketDTO marketDTO) {
        return marketService.createMarket(marketDTO);
    }
}
