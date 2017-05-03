package com.tenx.ms.retail.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.tenx.ms.retail.service.OrderService;
import com.tenx.ms.retail.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/v1/orders")
@Api(value="/orders", description="operations to manage orders")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Create Order")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=412, message="Precondition failure"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = "/{storeId:\\d+}", method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@PathVariable("storeId") Long storeId, @Validated @RequestBody OrderDTO order){
        order.setStoreId(storeId);
        order.setStatus("ORDERED");

        logger.debug("Create and order", order);
        OrderDTO createdOrder = orderService.createOrder(order);

        return new ResponseEntity<OrderDTO>(createdOrder, HttpStatus.OK);

    }


}
