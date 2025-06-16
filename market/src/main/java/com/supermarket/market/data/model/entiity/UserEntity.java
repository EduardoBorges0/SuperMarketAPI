package com.supermarket.market.data.model.entiity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String username;
    public Double credits;
}
