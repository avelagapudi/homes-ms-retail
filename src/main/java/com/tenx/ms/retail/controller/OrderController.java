package com.tenx.ms.retail.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.tenx.ms.retail.service.OrderService;

@RestController
@RequestMapping("/v1/orders")
@Api(value="/orders", description="operations to manage orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/{storeId:\\\\d+}", method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@PathVariable("storeId") Long storeId) {
        return new ResponseEntity<String>(HttpStatus.OK);

    }

}
