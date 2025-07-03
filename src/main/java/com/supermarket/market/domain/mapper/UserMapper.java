package com.supermarket.market.domain.mapper;

import com.supermarket.market.data.model.entiity.UserEntity;
import com.supermarket.market.domain.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserMapper() {}

    public UserDTO toDTO(UserEntity user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setCredits(user.getCredits());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    };
    public UserEntity toEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setCredits(userDTO.getCredits());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        return userEntity;
    };
}
