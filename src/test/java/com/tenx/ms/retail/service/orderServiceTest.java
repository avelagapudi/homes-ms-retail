package com.tenx.ms.retail.service;

import com.tenx.ms.retail.dto.OrderDTO;
import com.tenx.ms.retail.dto.ProductOrderDTO;
import com.tenx.ms.retail.entity.OrderEntity;
import com.tenx.ms.retail.entity.ProductOrderEntity;
import com.tenx.ms.retail.repository.OrderRepository;
import com.tenx.ms.retail.service.StockService;
import com.tenx.ms.retail.dto.StockDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
public class orderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Mock
    private StockService stockService;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createOrderTest(){

        ProductOrderEntity productOrder = new ProductOrderEntity();
        productOrder.setProductId(1L);
        productOrder.setCount(5);

        Set<ProductOrderEntity> productOrders = new HashSet();
        productOrders.add(productOrder);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStoreId(1L);
        orderEntity.setOrderDate(new Date());
        orderEntity.setStatus(OrderEntity.OrderStatus.ORDERED);
        orderEntity.setProducts(productOrders);
        orderEntity.setFirstName("testFirstName");
        orderEntity.setLastName("testLastName");
        orderEntity.setEmail("test@ten-x.com");
        orderEntity.setPhone("2134567890");

        ProductOrderDTO product = new ProductOrderDTO();
        product.setProductId(1L);
        product.setCount(5);

        Set<ProductOrderDTO> products = new HashSet();
        products.add(product);

        OrderDTO order = new OrderDTO();
        order.setStoreId(1L);
        order.setProducts(products);
        order.setFirstName("testFirstName");
        order.setLastName("testLastName");
        order.setEmail("test@ten-x.com");
        order.setPhone("2134567890");
        order.setStatus("ORDERED");
        order.setOrderDate(new Date());

        StockDTO stock = new StockDTO();
        stock.setStoreId(1L);
        stock.setProductId(1L);
        stock.setCount(5);

        when(stockService.isStockAvailable(any(StockDTO.class))).thenReturn(true);
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        OrderDTO result = orderService.createOrder(order);
        assertNotNull(result);
        assertEquals(order.getStoreId(), result.getStoreId());
        assertEquals(order.getProducts().size(), result.getProducts().size());
        assertEquals(order.getFirstName(), result.getFirstName());
        assertEquals(order.getLastName(), result.getLastName());
        assertEquals(order.getEmail(), result.getEmail());
        assertEquals(order.getPhone(), result.getPhone());
        assertEquals(order.getStatus(), result.getStatus());
        assertEquals(order.getOrderDate(), result.getOrderDate());

    }


    @Test(expected=NoSuchElementException.class)
    public void createOrderExceptionTest(){

        ProductOrderEntity productOrder = new ProductOrderEntity();
        productOrder.setProductId(1L);
        productOrder.setCount(5);

        Set<ProductOrderEntity> productOrders = new HashSet();
        productOrders.add(productOrder);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStoreId(1L);
        orderEntity.setOrderDate(new Date());
        orderEntity.setStatus(OrderEntity.OrderStatus.ORDERED);
        orderEntity.setProducts(productOrders);
        orderEntity.setFirstName("testFirstName");
        orderEntity.setLastName("testLastName");
        orderEntity.setEmail("test@ten-x.com");
        orderEntity.setPhone("2134567890");

        ProductOrderDTO product = new ProductOrderDTO();
        product.setProductId(1L);
        product.setCount(5);

        Set<ProductOrderDTO> products = new HashSet();
        products.add(product);

        OrderDTO order = new OrderDTO();
        order.setStoreId(1L);
        order.setProducts(products);
        order.setFirstName("testFirstName");
        order.setLastName("testLastName");
        order.setEmail("test@ten-x.com");
        order.setPhone("2134567890");
        order.setStatus("ORDERED");
        order.setOrderDate(new Date());

        StockDTO stock = new StockDTO();
        stock.setStoreId(1L);
        stock.setProductId(1L);
        stock.setCount(5);

        when(stockService.isStockAvailable(any(StockDTO.class))).thenReturn(false);
        when(orderRepository.save(any(OrderEntity.class))).thenThrow(new NoSuchElementException());

        OrderDTO result = orderService.createOrder(order);

    }


}
