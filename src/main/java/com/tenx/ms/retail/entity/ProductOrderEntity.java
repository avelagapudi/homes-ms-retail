package com.tenx.ms.retail.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_order")
public class ProductOrderEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="product_order_id")
    private Long productOrderId;

    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderEntity order;

    @Column(name="product_id")
    private Long productId;

    private Integer count;

    public Long getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Long productOrderId) {
        this.productOrderId = productOrderId;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
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
