package com.supermarket.market.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.supermarket.market.data.model.MarketEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Data
public class ProductsDTO {
    private Long id;

    private String product_name;

    private Double value;

    private Integer stock;

    private String category;

    @JsonBackReference
    private MarketDTO market;
}
