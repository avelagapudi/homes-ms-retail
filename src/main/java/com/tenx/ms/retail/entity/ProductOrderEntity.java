package com.tenx.ms.retail.entity;

import javax.persistence.*;

/**
 * Created by anupamav on 3/21/17.
 */
@Entity
@Table(name = "product_order")
public class ProductOrderEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long productOrderId;

    private Long orderId;
    private Long storeId;
    private Long productId;

    public Long getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Long productOrderId) {
        this.productOrderId = productOrderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
}
