package com.supermarket.market.data.repositories;

import com.supermarket.market.data.model.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<MarketEntity, Long> {
}
