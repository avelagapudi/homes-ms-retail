package com.tenx.ms.retail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductOrderDTO {

    @JsonProperty(value="product_id")
    private Long productId;

    @JsonProperty(value="count")
    private Integer count;

    public ProductOrderDTO(){

    }

    public ProductOrderDTO(Long productId, Integer count){
        this.productId = productId;
        this.count = count;
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
