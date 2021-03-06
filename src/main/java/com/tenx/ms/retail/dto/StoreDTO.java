package com.tenx.ms.retail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class StoreDTO {
    @JsonProperty("store_id")
    private Long storeId;

    @JsonProperty("store_name")
    @NotEmpty
    private String storeName;

    public StoreDTO() {

    }

    public StoreDTO(Long storeId, String storeName) {
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public StoreDTO(String storeName) {
        this.storeName = storeName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
