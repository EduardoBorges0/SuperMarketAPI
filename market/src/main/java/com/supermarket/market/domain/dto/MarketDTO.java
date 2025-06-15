package com.supermarket.market.domain.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.supermarket.market.data.model.ProductsEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class MarketDTO {
    private Long marketId;
    private String marketName;
    private Double marketCredits;
    @JsonManagedReference
    private List<ProductsDTO> products = new ArrayList<>();
}
