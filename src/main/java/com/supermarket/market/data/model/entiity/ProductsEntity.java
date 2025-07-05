package com.supermarket.market.data.model.entiity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "market")
@Entity(name = "products")
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String product_name;

    private Double value;

    private Integer stock;

    private String category;

    @ManyToOne
    @JoinColumn(name = "marketId", referencedColumnName = "marketId")
    private MarketEntity market;

    @ManyToMany(mappedBy = "products")
    private Set<UserEntity> users = new HashSet<>();
}