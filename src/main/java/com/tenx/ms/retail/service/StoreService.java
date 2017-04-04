package com.tenx.ms.retail.service;

import com.tenx.ms.retail.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.tenx.ms.retail.dto.StoreDTO;
import com.tenx.ms.retail.entity.StoreEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.tenx.ms.commons.rest.dto.ResourceCreated;

/**
 * Created by anupamav on 3/21/17.
 */
@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public ResourceCreated<Long> addStore(StoreDTO store) {
        StoreEntity storeEntity = new StoreEntity();

        storeEntity.setStoreId(store.getStoreId());
        storeEntity.setStoreName(store.getStoreName());

        storeEntity = storeRepository.save(storeEntity);

        return new ResourceCreated<>(storeEntity.getStoreId());
        //return convertToDTO(storeEntity);

    }

    public List<StoreDTO> getAllStores() {
        List<StoreEntity> stores = storeRepository.findAll();
        //return convertToDTOs(stores);

        return stores.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    private List<StoreDTO> convertToDTOs(List<StoreEntity> models) {
//        return models.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

    public StoreDTO getStoreById(Long storeId) {
        StoreEntity result = storeRepository.findByStoreId(storeId);
        return convertToDTO(result);
    }

    public StoreDTO getStoreByName(String storeName) {
        StoreEntity result = storeRepository.findByStoreName(storeName);
        return convertToDTO(result);
    }

    public StoreDTO convertToDTO(StoreEntity storeEntity) {
        StoreDTO dto = new StoreDTO();

        dto.setStoreId(storeEntity.getStoreId());
        dto.setStoreName(storeEntity.getStoreName());

        return dto;
    }


}
