package com.supermarket.market;

import com.supermarket.market.data.model.entiity.ProductsEntity;
import com.supermarket.market.data.repositories.ProductsRepository;
import com.supermarket.market.domain.dto.ProductsDTO;
import com.supermarket.market.domain.mapper.ProductMapper;
import com.supermarket.market.domain.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductUnitTests {

    @Mock
    private ProductsRepository productsRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateProduct() {
        // Arrange
        ProductsDTO createProduct = new ProductsDTO();
        createProduct.setProduct_name("Arroz 1Kg");
        createProduct.setCategory("Food");
        createProduct.setValue(15.0);
        createProduct.setStock(10);

        ProductsEntity entity = new ProductsEntity();
        entity.setProduct_name("Arroz 1Kg");
        entity.setCategory("Food");
        entity.setValue(15.0);
        entity.setStock(10);

        when(productMapper.toEntity(any())).thenReturn(entity);
        when(productsRepository.save(any())).thenReturn(entity);

        ResponseEntity<?> result = productService.createProduct(createProduct);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(productMapper).toEntity(any());
        verify(productsRepository).save(any());
    }

    @Test
    void testGetProductById(){
        //Arrange
        Long id = 1L;
        ProductsDTO dto = new ProductsDTO();
        dto.setId(1L);
        dto.setProduct_name("Arroz 1Kg");
        dto.setCategory("Food");
        dto.setValue(15.0);
        dto.setStock(10);

        when(productsRepository.existsById(id)).thenReturn(Objects.equals(dto.getId(), id));
        //Act
        ResponseEntity<?> result = productService.getProductById(id);
        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
