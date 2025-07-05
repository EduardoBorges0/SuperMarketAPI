package com.supermarket.market.domain.mapper;

import com.supermarket.market.data.model.entiity.ProductsEntity;
import com.supermarket.market.data.model.entiity.UserEntity;
import com.supermarket.market.data.model.entiity.UserProductEntity;
import com.supermarket.market.domain.dto.ProductsDTO;
import com.supermarket.market.domain.dto.UserDTO;
import com.supermarket.market.domain.dto.UserProductDTO;
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

        List<UserProductDTO> userProductDTOs = user.getUserProducts()
                .stream()
                .map(up -> {
                    UserProductDTO dto = new UserProductDTO();
                    dto.setProduct(productMapper.toDTO(up.getProduct()));
                    dto.setQuantity(up.getQuantity());
                    return dto;
                })
                .collect(Collectors.toList());

        userDTO.setUserProducts(userProductDTOs);

        return userDTO;
    }


    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setCredits(userDTO.getCredits());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());

        Set<UserProductEntity> userProducts = userDTO.getUserProducts()
                .stream()
                .map(dto -> {
                    UserProductEntity entity = new UserProductEntity();
                    entity.setUser(userEntity); // importante para relação bidirecional
                    entity.setProduct(productMapper.toEntity(dto.getProduct()));
                    entity.setQuantity(dto.getQuantity());
                    return entity;
                })
                .collect(Collectors.toSet());

        userEntity.setUserProducts(userProducts);

        return userEntity;
    }

}
