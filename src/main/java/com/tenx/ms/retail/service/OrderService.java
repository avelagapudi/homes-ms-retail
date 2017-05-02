package com.tenx.ms.retail.service;

import com.tenx.ms.retail.entity.ProductOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenx.ms.retail.repository.OrderRepository;
import com.tenx.ms.retail.dto.OrderDTO;
import com.tenx.ms.retail.entity.OrderEntity;
import com.tenx.ms.retail.dto.ProductOrderDTO;
import com.tenx.ms.retail.dto.StockDTO;
import com.tenx.ms.retail.service.StockService;
import java.util.Set;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockService stockService;

    @Transactional
    public OrderDTO createOrder(OrderDTO order){

        //check if stock exists for the product
        Set<ProductOrderDTO> products = order.getProducts();

        products.forEach(product->checkStockAvailable(order.getStoreId(),product.getProductId(),product.getCount()));

        //If yes then save the order otherwise throw exception
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStoreId(order.getStoreId());
        orderEntity.setOrderDate(order.getOrderDate());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setProducts(
                order.getProducts().stream()
                        .map(product->convertToProductOrderEntity(orderEntity, product))
                        .collect(Collectors.toSet())
        );
        orderEntity.setFirstName(order.getFirstName());
        orderEntity.setLastName(order.getLastName());
        orderEntity.setEmail(order.getEmail());
        orderEntity.setPhone(order.getPhone());

        OrderEntity result = orderRepository.save(orderEntity);

        //update the stock count for each product after order is successful
        products.forEach(product->updateStockCount(order.getStoreId(),product.getProductId(),product.getCount()));

        return convertToDTO(result);

    }

    private void checkStockAvailable(Long storeId, Long productId, Integer count) {
        StockDTO stock = new StockDTO();
        stock.setStoreId(storeId);
        stock.setProductId(productId);
        stock.setCount(count);

        if(!stockService.isStockAvailable(stock)){
            throw new NoSuchElementException("Stock not available for the product");
        }
    }

    private void updateStockCount(Long storeId, Long productId, Integer count){
        StockDTO stock = new StockDTO();
        stock.setStoreId(storeId);
        stock.setProductId(productId);
        stock.setCount(-count);

        stockService.updateStock(stock);
    }

    private ProductOrderEntity convertToProductOrderEntity(OrderEntity orderEntity, ProductOrderDTO product) {
        ProductOrderEntity productOrderEntity = new ProductOrderEntity();

        productOrderEntity.setOrder(orderEntity);
        productOrderEntity.setProductId(product.getProductId());
        productOrderEntity.setCount(product.getCount());

        return productOrderEntity;
    }

    private OrderDTO convertToDTO(OrderEntity orderEntity) {
        OrderDTO dto = new OrderDTO();
        dto.setStoreId(orderEntity.getStoreId());
        dto.setOrderDate(orderEntity.getOrderDate());
        dto.setProducts(orderEntity.getProducts().stream()
                .map(this::convertToProductOrderDTO)
                .collect(Collectors.toSet()));
        dto.setFirstName(orderEntity.getFirstName());
        dto.setLastName(orderEntity.getLastName());
        dto.setEmail(orderEntity.getEmail());
        dto.setPhone(orderEntity.getPhone());
        dto.setStatus(orderEntity.getStatus());

        return dto;
    }

    private ProductOrderDTO convertToProductOrderDTO(ProductOrderEntity product){
        ProductOrderDTO dto = new ProductOrderDTO();
        dto.setProductId(product.getProductId());
        dto.setCount(product.getCount());

        return dto;

    }


}
