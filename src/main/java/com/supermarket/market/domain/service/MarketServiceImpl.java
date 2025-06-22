package com.supermarket.market.domain.service;

import com.supermarket.market.data.model.entiity.MarketEntity;
import com.supermarket.market.data.model.response.MessageError;
import com.supermarket.market.data.repositories.MarketRepository;
import com.supermarket.market.domain.dto.market.MarketDTO;
import com.supermarket.market.domain.mapper.MarketMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MarketServiceImpl {

    private MarketRepository marketRepository;
    private MarketMapper marketMapper;

    public MarketServiceImpl(MarketRepository marketRepository, MarketMapper marketMapper) {
        this.marketRepository = marketRepository;
        this.marketMapper = marketMapper;
    }

    public ResponseEntity<?> getEveryMarket() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(marketRepository
                            .findAll()
                            .stream()
                            .map(marketMapper::toDTO)
                            .toList());
        } catch (Exception e) {
            MessageError error = new MessageError();
            error.setMessage("Erro interno ao pegar todos os mercados: " + e.getMessage());
            error.setCode(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    public ResponseEntity<?> getMarketById(Long id) {
        try {
            boolean isError = id == null || !marketRepository.existsById(id);
            if (isError) {
                MessageError messageError = new MessageError();
                messageError.setMessage("Id not exist!!");
                messageError.setCode(404);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(marketRepository
                                .findById(id)
                                .map(marketMapper::toDTO)
                                .orElse(null));
            }
        } catch (Exception e) {
            MessageError error = new MessageError();
            error.setMessage("Erro interno ao pegar mercado por ID: " + e.getMessage());
            error.setCode(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    public ResponseEntity<?> createMarket(MarketDTO marketDTO) {
        try {
            boolean isError = marketDTO.getMarketName() == null || marketDTO.getMarketCredits() == null;
            if (isError) {
                MessageError error = new MessageError();
                error.setMessage("Nome do mercado e créditos são obrigatórios.");
                error.setCode(400);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            MarketEntity toEntity = marketMapper.toEntity(marketDTO);
            marketRepository.save(toEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(marketDTO);
        } catch (Exception e) {
            MessageError error = new MessageError();
            error.setMessage("Erro interno ao salvar mercado: " + e.getMessage());
            error.setCode(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    public void deleteMarket(Long id) {
        marketRepository.deleteById(id);
    }
}
