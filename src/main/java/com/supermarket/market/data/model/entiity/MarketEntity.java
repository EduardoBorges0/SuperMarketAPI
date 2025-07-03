package com.supermarket.market.data.model.entiity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "products") // 👈 evita a chamada recursiva
@Entity(name = "supermarket")
public class MarketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long marketId;
    private String marketName;
    private Double marketCredits;
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductsEntity> products;
}
