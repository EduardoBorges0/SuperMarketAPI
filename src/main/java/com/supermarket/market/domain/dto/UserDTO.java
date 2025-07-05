package com.supermarket.market.domain.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String username;
    private Double credits;
    private List<UserProductDTO> userProducts = List.of(); // substitui products
}
