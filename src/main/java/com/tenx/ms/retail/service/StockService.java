package com.tenx.ms.retail.service;

import com.tenx.ms.commons.rest.dto.ResourceCreated;
import com.tenx.ms.retail.entity.StockEntity;
import com.tenx.ms.retail.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.tenx.ms.retail.dto.StockDTO;

/**
 * Created by anupamav on 3/21/17.
 */
@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public StockDTO updateStock(StockDTO stock){
        StockEntity stockEntity = new StockEntity();

        Long storeId = stock.getStoreId();
        Long productId = stock.getProductId();
        Integer updatedCount = stock.getCount();

        //Check if a record exists for the storeId and productId
        StockEntity result = stockRepository.findByStoreIdAndProductId(storeId, productId);

        if (result != null) {
            Long stockId = result.getStockId();
            updatedCount = result.getCount() + stock.getCount();
            stockEntity.setStockId(stockId);
        }

        stockEntity.setStoreId(stock.getStoreId());
        stockEntity.setProductId(stock.getProductId());
        stockEntity.setCount(updatedCount);

        stockEntity = stockRepository.save(stockEntity);

        //return new ResourceCreated<>(stockEntity.getStockId());
        return convertToDTO(stockEntity);

    }

    public StockDTO convertToDTO(StockEntity stockEntity) {
        StockDTO dto = new StockDTO();

        dto.setStockId(stockEntity.getStockId());
        dto.setStoreId(stockEntity.getStoreId());
        dto.setProductId(stockEntity.getProductId());

        return dto;
    }

}
