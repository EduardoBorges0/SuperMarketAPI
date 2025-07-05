package com.supermarket.market.domain.dto;

import lombok.Data;

@Data
public class UserProductDTO {
    private ProductsDTO product;
    private Integer quantity;
    // getters e setters
}
