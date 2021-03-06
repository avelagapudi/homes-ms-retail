package com.tenx.ms.retail.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
import com.tenx.ms.commons.validation.constraints.PhoneNumber;
import com.tenx.ms.commons.validation.constraints.Email;


@Entity
@Table(name = "orders")
public class OrderEntity {

    public enum OrderStatus {
        ORDERED, PACKING, SHIPPED
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="order_id")
    private Long orderId;

    @Column(name="store_id")
    private Long storeId;

    @Column(name="order_date")
    private Date orderDate;

    @NotNull
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Email
    private String email;

    @PhoneNumber
    private String phone;

    @NotNull
    private OrderStatus status;

    @NotNull
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<ProductOrderEntity> products;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Set<ProductOrderEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductOrderEntity> products) {
        this.products = products;
    }
}
