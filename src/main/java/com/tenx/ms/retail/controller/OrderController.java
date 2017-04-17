package com.tenx.ms.retail.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.tenx.ms.retail.service.OrderService;
import com.tenx.ms.retail.dto.OrderDTO;

@RestController
@RequestMapping("/v1/orders")
@Api(value="/orders", description="operations to manage orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/{storeId:\\d+}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createOrder(@PathVariable("storeId") Long storeId, @Validated @RequestBody OrderDTO order) {
        order.setStoreId(storeId);

        OrderDTO createdOrder = orderService.createOrder(order);
        return new ResponseEntity<OrderDTO>(createdOrder, HttpStatus.CREATED);
    }


}
