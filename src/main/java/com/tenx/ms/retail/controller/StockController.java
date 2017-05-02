package com.tenx.ms.retail.controller;

import com.tenx.ms.retail.service.ProductService;
import com.tenx.ms.retail.service.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import com.tenx.ms.retail.dto.StockDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.tenx.ms.retail.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/v1/stock")
@Api(value="/stock", description = "Manage stock for products in a store")
public class StockController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Update stock for store and product")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=412, message="Precondition failure"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = "/{storeId:\\d+}/{productId:\\d+}", method = RequestMethod.POST)
    public ResponseEntity<?> updateStock(@PathVariable("storeId") Long storeId, @PathVariable("productId") Long productId, @Validated @RequestBody StockDTO stock){
        //Check if the product and store record exists
        logger.debug("check if product exists for store");
        ProductDTO product = productService.getProductByStoreIdProductId(storeId, productId);
        if (product == null) {
            return new ResponseEntity<String>("Product and Store match not found", HttpStatus.NOT_FOUND);
        }

        stock.setStoreId(storeId);
        stock.setProductId(productId);

        //Add stock for a product under store
        logger.debug("update the stock for store and product", stock);
        StockDTO updatedStock = stockService.updateStock(stock);

        return new ResponseEntity<StockDTO>(updatedStock, HttpStatus.CREATED);
    }

}
