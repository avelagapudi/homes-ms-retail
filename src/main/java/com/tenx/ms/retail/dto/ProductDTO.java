package com.tenx.ms.retail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {

    @JsonProperty(value="product_id")
    private Long productId;

    @JsonProperty(value="store_id")
    private Long storeId;

    private String name;
    private String description;
    private String sku;
    private Double price;

    public ProductDTO(){}

    public ProductDTO(Long productId, Long storeId, String name, String description, String sku, Double price){
        this.productId = productId;
        this.storeId = storeId;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
    }

    public ProductDTO(Long storeId, String name, String description, String sku, Double price) {
        this.storeId = storeId;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
