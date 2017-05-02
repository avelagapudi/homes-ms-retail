package com.tenx.ms.retail.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stores")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "store_name")
    @NotNull
    private String storeName;

    public StoreEntity() {

    }

    public StoreEntity(Long storeId, String storeName) {
        this.storeId = storeId;
        this.storeName =storeName;
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
