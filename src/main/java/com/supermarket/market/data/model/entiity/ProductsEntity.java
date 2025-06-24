package com.supermarket.market.data.model.entiity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "products")
@Data
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String product_name;

    private Double value;

    private Integer stock;

    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "marketId", referencedColumnName = "marketId")
    private MarketEntity market;
}