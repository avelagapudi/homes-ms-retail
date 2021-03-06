package com.tenx.ms.retail.service;

import com.tenx.ms.retail.entity.StockEntity;
import com.tenx.ms.retail.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.tenx.ms.retail.dto.StockDTO;

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

         return convertToDTO(stockEntity);

    }

    public StockDTO convertToDTO(StockEntity stockEntity) {
        StockDTO dto = new StockDTO();

        dto.setStockId(stockEntity.getStockId());
        dto.setStoreId(stockEntity.getStoreId());
        dto.setProductId(stockEntity.getProductId());
        dto.setCount(stockEntity.getCount());

        return dto;
    }

    public boolean isStockAvailable(StockDTO stock) {
        StockEntity result = stockRepository.findByStoreIdAndProductId(stock.getStoreId(), stock.getProductId());

        if (result == null) {
            return false;
        } else {
            Integer stockCount = result.getCount();

            if (stockCount >= stock.getCount()) {
                return true;
            } else {
                return false;
            }
        }
    }

}
