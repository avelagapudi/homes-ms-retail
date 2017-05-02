package com.tenx.ms.retail.service;

import com.tenx.ms.retail.dto.StockDTO;
import com.tenx.ms.retail.entity.StockEntity;
import com.tenx.ms.retail.repository.StockRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class stockServiceTest {

    @InjectMocks
    private StockService stockService;

    @Mock
    private StockRepository stockRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addStockTest(){
        StockEntity stock = new StockEntity();
        stock.setStoreId(1L);
        stock.setProductId(1L);
        stock.setCount(15);

        StockDTO dto = new StockDTO();
        dto.setStoreId(1L);
        dto.setProductId(1L);
        dto.setCount(15);

        when(stockRepository.save(any(StockEntity.class))).thenReturn(stock);
        StockDTO result = stockService.updateStock(dto);

        assertNotNull(result);
        assertEquals(dto.getStoreId(), result.getStoreId());
        assertEquals(dto.getProductId(), result.getProductId());
        assertEquals(dto.getCount(), result.getCount());


    }

    @Test
    public void updateStockTest(){
        StockEntity existingStock = new StockEntity();
        existingStock.setStockId(1L);
        existingStock.setStoreId(1L);
        existingStock.setProductId(1L);
        existingStock.setCount(10);

        StockEntity stock = new StockEntity();
        stock.setStockId(1L);
        stock.setStoreId(1L);
        stock.setProductId(1L);
        stock.setCount(25);

        StockDTO dto = new StockDTO();
        dto.setStoreId(1L);
        dto.setProductId(1L);
        dto.setCount(15);

        StockDTO updatedDTO = new StockDTO();
        updatedDTO.setStoreId(1L);
        updatedDTO.setProductId(1L);
        updatedDTO.setCount(25);

        when(stockRepository.findByStoreIdAndProductId(1L,1L)).thenReturn(existingStock);
        when(stockRepository.save(any(StockEntity.class))).thenReturn(stock);

        StockDTO result = stockService.updateStock(dto);

        assertNotNull(result);
        assertEquals(updatedDTO.getStoreId(), result.getStoreId());
        assertEquals(updatedDTO.getProductId(), result.getProductId());
        assertEquals(updatedDTO.getCount(),result.getCount());

    }

    @Test
    public void isStockAvailableTrueTest(){
        StockEntity existingStock = new StockEntity();
        existingStock.setStockId(1L);
        existingStock.setStoreId(1L);
        existingStock.setProductId(1L);
        existingStock.setCount(10);

        StockDTO dto = new StockDTO();
        dto.setStoreId(1L);
        dto.setProductId(1L);
        dto.setCount(5);

        when(stockRepository.findByStoreIdAndProductId(1L,1L)).thenReturn(existingStock);

        boolean result = stockService.isStockAvailable(dto);

        assertEquals(true, result);

    }

    @Test
    public void isStockAvailableFalseTest(){
        StockEntity existingStock = new StockEntity();
        existingStock.setStockId(1L);
        existingStock.setStoreId(1L);
        existingStock.setProductId(1L);
        existingStock.setCount(10);

        StockDTO dto = new StockDTO();
        dto.setStoreId(1L);
        dto.setProductId(1L);
        dto.setCount(20);

        when(stockRepository.findByStoreIdAndProductId(1L,1L)).thenReturn(existingStock);

        boolean result = stockService.isStockAvailable(dto);

        assertEquals(false, result);
    }

}
