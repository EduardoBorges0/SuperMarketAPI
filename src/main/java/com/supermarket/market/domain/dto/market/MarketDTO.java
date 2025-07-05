package com.supermarket.market.domain.dto.market;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.supermarket.market.domain.dto.ProductsDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MarketDTO {
    private Long marketId;
    private String marketName;
    private Double marketCredits;
    private List<ProductsDTO> products = new ArrayList<>();
}

