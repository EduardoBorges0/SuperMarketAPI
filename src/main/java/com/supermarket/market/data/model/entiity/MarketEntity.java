package com.supermarket.market.data.model.entiity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity(name = "supermarket")
@Data
public class MarketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long marketId;
    private String marketName;
    private Double marketCredits;
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductsEntity> products;
}
