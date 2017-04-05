package com.tenx.ms.retail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by anupamav on 4/4/17.
 */
public class StockDTO {

    @JsonProperty(value="stock_id")
    private Long stockId;

    @JsonProperty(value="store_id")
    private Long storeId;

    @JsonProperty(value="product_id")
    private Long productId;

    @JsonProperty(value="count")
    private Integer count;

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
