package com.supermarket.market.data.model.entiity;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class AuthRequest {
    private String role;
    private String email;
    private String password;
}
