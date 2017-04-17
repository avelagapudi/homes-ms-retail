package com.tenx.ms.retail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Set;

public class OrderDTO {

    @JsonProperty(value="store_id")
    private Long storeId;

    @JsonProperty(value="order_date")
    private Date orderDate;

    @JsonProperty(value="products")
    private Set<ProductOrderDTO> products;

    @JsonProperty(value="first_name")
    private String firstName;

    @JsonProperty(value="last_name")
    private String lastName;

    private String email;
    private String phone;
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
