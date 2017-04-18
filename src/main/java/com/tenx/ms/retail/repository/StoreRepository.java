package com.tenx.ms.retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenx.ms.retail.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Long>{

    StoreEntity findByStoreId(Long storeId);

    StoreEntity findByStoreName(String storeName);

}
