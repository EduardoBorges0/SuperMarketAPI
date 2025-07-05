package com.supermarket.market.domain.service;

import com.supermarket.market.data.model.entiity.ProductsEntity;
import com.supermarket.market.data.model.entiity.UserEntity;
import com.supermarket.market.data.model.entiity.UserProductEntity;
import com.supermarket.market.data.model.response.MessageError;
import com.supermarket.market.data.repositories.UserRepository;
import com.supermarket.market.domain.dto.ProductsDTO;
import com.supermarket.market.domain.dto.UserDTO;
import com.supermarket.market.domain.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    private ProductServiceImpl productService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ResponseEntity<?> createUser(String username, String email, String password) {
        try {
            UserEntity entity = new UserEntity();
            entity.setUsername(username);
            entity.setEmail(email);
            entity.setPassword(password);
            userRepository.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);
        } catch (Exception e) {
            MessageError messageError = new MessageError();
            messageError.setCode(500);
            messageError.setMessage("e.getMessage()");
            return ResponseEntity.status(500).body(messageError);
        }
    }

    public ResponseEntity<?> getEveryUsers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll().stream().map(userMapper::toDTO).toList());
        } catch (Exception e) {
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

    public void addProductToUser(Long userId, Long productId, Integer quantity) {
        ProductsEntity product = productService.findEntityById(productId);
        UserEntity user = userRepository.findById(userId).orElseThrow();
        productService.stockValueUpdate(productId, product.getStock(), quantity);

        UserProductEntity userProduct = new UserProductEntity();
        userProduct.setUser(user);
        userProduct.setProduct(product);
        userProduct.setQuantity(quantity);

        user.getUserProducts().add(userProduct);
        userRepository.save(user);
    }
    public void removeProductFromUser(Long userId, Long productId){
        ProductsEntity product = productService.findEntityById(productId);
        UserEntity user = userRepository.findById(userId).orElseThrow();
        Integer quantity = user.getUserProducts().stream()
                .filter(up -> up.getProduct().getId().equals(productId))
                .map(UserProductEntity::getQuantity)
                .findFirst() // pega o primeiro que encontrar (assumindo que só tem um)
                .orElse(null); // ou 0 se quiser padrão
        user.getUserProducts().removeIf(up -> up.getProduct().getId().equals(productId));
        userRepository.save(user);
        productService.stockValueUpdate(productId, product.getStock(), -quantity);

    }
}
