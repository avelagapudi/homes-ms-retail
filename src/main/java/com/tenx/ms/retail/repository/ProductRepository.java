package com.tenx.ms.retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenx.ms.retail.entity.ProductEntity;
import java.util.List;

/**
 * Created by anupamav on 3/21/17.
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

    List<ProductEntity> findByStoreId(Long storeId);

    ProductEntity findByStoreIdAndProductId(Long storeId, Long productId);

    ProductEntity findByStoreIdAndName(Long storeId, String productName);
}
