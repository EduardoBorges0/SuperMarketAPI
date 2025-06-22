package com.supermarket.market.domain.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String password;
    private String username;
    private Double credits;
}
