package com.tenx.ms.retail.repository;

import com.tenx.ms.retail.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by anupamav on 3/21/17.
 */
public interface StockRepository extends JpaRepository<StockEntity, Long> {
    StockEntity findByStoreIdAndProductId(Long storeId, Long productId);
}
