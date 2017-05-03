package com.tenx.ms.retail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class OrderDTO {
    private Long orderId;

    @JsonProperty(value="store_id")
    private Long storeId;

    @JsonProperty(value="order_date")
    private Date orderDate;

    @NotEmpty
    @JsonProperty(value="products")
    private Set<ProductOrderDTO> products;

    @NotEmpty
    @JsonProperty(value="first_name")
    private String firstName;

    @NotEmpty
    @JsonProperty(value="last_name")
    private String lastName;

    @NotEmpty
    private String email;
    private String phone;

    @NotNull
    private String status;

    public OrderDTO(){}

    public OrderDTO(Long orderId) {
        this.orderId = orderId;
    }

    public OrderDTO(Long storeId, String firstName, String lastName){
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Set<ProductOrderDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductOrderDTO> products) {
        this.products = products;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
