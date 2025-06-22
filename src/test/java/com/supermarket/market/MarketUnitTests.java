package com.supermarket.market;

import com.supermarket.market.data.model.entiity.MarketEntity;
import com.supermarket.market.data.repositories.MarketRepository;
import com.supermarket.market.domain.dto.market.MarketDTO;
import com.supermarket.market.domain.mapper.MarketMapper;
import com.supermarket.market.domain.service.MarketServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarketUnitTests {
	@Mock
	private MarketRepository marketRepository;

	@Mock
	private MarketMapper marketMapper;

	@InjectMocks
	private MarketServiceImpl marketService;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetMarketById() {
		// Arrange
		Long id = 1L;
		MarketEntity response = new MarketEntity();
		response.setMarketId(id);
		response.setMarketName("Extra");
		response.setMarketCredits(150.0);
		response.setProducts(new ArrayList<>());

		MarketDTO dto = new MarketDTO();
		dto.setMarketId(id);
		dto.setMarketName("Extra");
		dto.setMarketCredits(150.0);
		dto.setProducts(new ArrayList<>());

		when(marketRepository.existsById(id)).thenReturn(true);
		when(marketRepository.findById(id)).thenReturn(Optional.of(response));
		when(marketMapper.toDTO(response)).thenReturn(dto);

		// Act
		ResponseEntity<?> result = marketService.getMarketById(id);

		// Assert
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertTrue(result.getBody() instanceof MarketDTO);
		MarketDTO body = (MarketDTO) result.getBody();
		assertEquals("Extra", body.getMarketName());
		assertEquals(150.0, body.getMarketCredits());

		verify(marketRepository).existsById(id);
		verify(marketRepository).findById(id);
		verify(marketMapper).toDTO(response);
	}

	@Test
	void testCreateMarket() {
		//Arrange
		MarketDTO dto = new MarketDTO();
		dto.setMarketName("Extra");
		dto.setMarketCredits(150.0);
		dto.setProducts(new ArrayList<>());

		MarketEntity response = new MarketEntity();
		response.setMarketName("Extra");
		response.setMarketCredits(150.0);
		//Act
		when(marketMapper.toEntity(any())).thenReturn(response);
		marketService.createMarket(dto);
		ArgumentCaptor<MarketEntity> captor = ArgumentCaptor.forClass(MarketEntity.class);
		verify(marketRepository).save(captor.capture());
		MarketEntity savedEntity = captor.getValue();
		//Assert
		assertEquals("Extra", savedEntity.getMarketName());
		assertEquals(150.0, savedEntity.getMarketCredits());
	}

	@Test
	void testGetEveryMarket(){
		marketService.getEveryMarket();
		verify(marketRepository).findAll();
	}
}
