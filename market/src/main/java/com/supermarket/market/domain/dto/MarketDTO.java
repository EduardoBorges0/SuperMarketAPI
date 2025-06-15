package com.supermarket.market.domain.dto;

import com.supermarket.market.data.model.ProductsEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class MarketDTO {
    private Long marketId;
    private String marketName;
    private Double marketCredits;
    private List<ProductsDTO> products;
}
