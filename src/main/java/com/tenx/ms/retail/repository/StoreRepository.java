package com.tenx.ms.retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenx.ms.retail.entity.StoreEntity;

/**
 * Created by anupamav on 3/21/17.
 */
public interface StoreRepository extends JpaRepository<StoreEntity, Long>{

    StoreEntity findByStoreId(Long storeId);

    StoreEntity findByStoreName(String storeName);

}
