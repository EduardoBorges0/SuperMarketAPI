package com.supermarket.market.presentation.controller;

import com.supermarket.market.domain.dto.market.MarketDTO;
import com.supermarket.market.domain.service.MarketServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market")
public class MarketController {
    private MarketServiceImpl marketService;

    public MarketController(MarketServiceImpl marketService) {
        this.marketService = marketService;
    }
    @GetMapping("/getMarketById/{id}")
    public MarketDTO getMarketById(@PathVariable Long id){
        return marketService.getMarketById(id);
    }
    @GetMapping("/getEveryMarket")
    public Iterable<MarketDTO> getEveryMarket(){
        return marketService.getEveryMarket();
    }
    @PostMapping("/createMarket")
    public MarketDTO createMarket(@RequestBody MarketDTO marketDTO) {
        return marketService.createMarket(marketDTO);
    }
    @DeleteMapping("/deleteMarket/{id}")
    public void deleteMarket(@PathVariable Long id){
        marketService.deleteMarket(id);
    }
}
