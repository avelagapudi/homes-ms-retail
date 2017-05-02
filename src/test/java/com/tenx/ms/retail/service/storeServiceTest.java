package com.tenx.ms.retail.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.tenx.ms.retail.service.StoreService;
import com.tenx.ms.retail.repository.StoreRepository;
import com.tenx.ms.retail.entity.StoreEntity;
import com.tenx.ms.retail.dto.StoreDTO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class storeServiceTest {

    @InjectMocks
    private StoreService storeService;

    @Mock
    private StoreRepository storeRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addStoreTest(){
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setStoreId(1L);
        storeEntity.setStoreName("testStore");

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreId(1L);
        storeDTO.setStoreName("testStore");

        when(storeRepository.save(any(StoreEntity.class))).thenReturn(storeEntity);
        StoreDTO addedStore = storeService.addStore(storeDTO);
        assertEquals(storeDTO.getStoreId(), addedStore.getStoreId());
        assertEquals(storeDTO.getStoreName(), addedStore.getStoreName());
    }

    @Test
    public void getAllStoresTest(){
        List<StoreEntity> storeList = new ArrayList<StoreEntity>();
        StoreEntity store1 = new StoreEntity(1L, "testStoreOne");
        storeList.add(store1);
        StoreEntity store2 = new StoreEntity(2L, "testStoreTwo");
        storeList.add(store2);

        List<StoreDTO> storeDTOList = new ArrayList<StoreDTO>();
        StoreDTO dto1 = new StoreDTO(1L, "testStoreOne");
        StoreDTO dto2 = new StoreDTO(2L, "testStoreTwo");

        storeDTOList.add(dto1);
        storeDTOList.add(dto2);

        when(storeRepository.findAll()).thenReturn(storeList);

        List<StoreDTO> result = new ArrayList<StoreDTO>();
        result = storeService.getAllStores();

        assertNotNull(result);
        assertEquals(storeDTOList.size(), result.size());

    }

    @Test
    public void getStoreByIdTest(){
        StoreEntity store = new StoreEntity(1L, "testStoreOne");
        StoreDTO dto = new StoreDTO(1L, "testStoreOne");

        when(storeRepository.findByStoreId(1L)).thenReturn(store);

        StoreDTO result = storeService.getStoreById(1L);
        assertEquals(dto.getStoreId(), result.getStoreId());
        assertEquals(dto.getStoreName(), result.getStoreName());

    }

    @Test
    public void getStoreByNameTest(){
        StoreEntity store = new StoreEntity(1L, "testStoreOne");
        StoreDTO dto = new StoreDTO(1L, "testStoreOne");

        when(storeRepository.findByStoreName("testStoreOne")).thenReturn(store);

        StoreDTO result = storeService.getStoreByName("testStoreOne");
        assertEquals(dto.getStoreId(), result.getStoreId());
        assertEquals(dto.getStoreName(), result.getStoreName());
    }
}
