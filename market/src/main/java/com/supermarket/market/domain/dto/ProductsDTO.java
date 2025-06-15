package com.supermarket.market.domain.dto;

import com.supermarket.market.data.model.MarketEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ProductsDTO {
    private Long id;

    private String product_name;

    private Double value;

    private Integer stock;

    private String category;

    private MarketDTO market;
}
