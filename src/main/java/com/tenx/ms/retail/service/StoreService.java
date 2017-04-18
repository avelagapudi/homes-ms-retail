package com.tenx.ms.retail.service;

import com.tenx.ms.retail.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.tenx.ms.retail.dto.StoreDTO;
import com.tenx.ms.retail.entity.StoreEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.tenx.ms.commons.rest.dto.ResourceCreated;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public StoreDTO addStore(StoreDTO store) {
        StoreEntity storeEntity = new StoreEntity();

        storeEntity.setStoreId(store.getStoreId());
        storeEntity.setStoreName(store.getStoreName());

        storeEntity = storeRepository.save(storeEntity);
        //return new ResourceCreated<>(storeEntity.getStoreId());
        return convertToDTO(storeEntity);

    }

    public List<StoreDTO> getAllStores() {
        List<StoreEntity> stores = storeRepository.findAll();

        return stores.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StoreDTO getStoreById(Long storeId) {
        StoreEntity result = storeRepository.findByStoreId(storeId);
        return convertToDTO(result);
    }

    public StoreDTO getStoreByName(String storeName) {
        StoreEntity result = storeRepository.findByStoreName(storeName);
        return convertToDTO(result);
    }


    public StoreDTO convertToDTO(StoreEntity storeEntity) {
        if (storeEntity == null ) {
            return null;
        }

        StoreDTO dto = new StoreDTO();

        dto.setStoreId(storeEntity.getStoreId());
        dto.setStoreName(storeEntity.getStoreName());

        return dto;
    }


}
