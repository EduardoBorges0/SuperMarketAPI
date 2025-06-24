package com.supermarket.market.domain.service;

import com.supermarket.market.data.model.entiity.UserEntity;
import com.supermarket.market.data.model.response.MessageError;
import com.supermarket.market.data.repositories.UserRepository;
import com.supermarket.market.domain.dto.UserDTO;
import com.supermarket.market.domain.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ResponseEntity<?> createUser(String username, String email, String password){
        try{
            UserEntity entity = new UserEntity();
            entity.setUsername(username);
            entity.setEmail(email);
            entity.setPassword(password);
            userRepository.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);
        }catch (Exception e){
            MessageError messageError = new MessageError();
            messageError.setCode(500);
            messageError.setMessage("e.getMessage()");
            return ResponseEntity.status(500).body(messageError);
        }
    }
    public ResponseEntity<?> getEveryUsers(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll().stream().map(userMapper::toDTO).toList());
        }catch (Exception e){
            MessageError messageError = new MessageError();
            messageError.setCode(500);
            messageError.setMessage("e.getMessage()");
            return ResponseEntity.status(500).body(messageError);
        }
    }
    public List<UserDTO> getAllUsersDTO() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
    public void getMe(){

    }

}
