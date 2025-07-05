package com.supermarket.market.domain.mapper;

import com.supermarket.market.data.model.entiity.ProductsEntity;
import com.supermarket.market.data.model.entiity.UserEntity;
import com.supermarket.market.domain.dto.ProductsDTO;
import com.supermarket.market.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    private ProductMapper productMapper;

    public UserMapper() {
    }

    public UserDTO toDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setCredits(user.getCredits());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        List<ProductsDTO> productsDTO = user
                .getProducts()
                .stream().map(productMapper::toDTO)
                .collect(Collectors.toList());
        userDTO.setProducts(productsDTO);
        return userDTO;
    }

    ;

    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setCredits(userDTO.getCredits());
        userEntity.setEmail(userDTO.getEmail());
        Set<ProductsEntity> productsEntity = userDTO.getProducts()
                .stream()
                .map(productMapper::toEntity)
                .collect(Collectors.toSet());

        userEntity.setProducts(productsEntity);

        userEntity.setPassword(userDTO.getPassword());
        return userEntity;
    };
}
