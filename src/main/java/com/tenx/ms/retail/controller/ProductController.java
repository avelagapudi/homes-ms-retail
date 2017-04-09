package com.tenx.ms.retail.controller;

import com.tenx.ms.commons.rest.dto.ResourceCreated;
import com.tenx.ms.retail.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.tenx.ms.retail.dto.ProductDTO;
import com.tenx.ms.retail.service.StoreService;
import com.tenx.ms.retail.dto.StoreDTO;
import java.util.List;

/**
 * Created by anupamav on 3/21/17.
 */
@RestController
@RequestMapping("/v1/products")
@Api(value="/products", description="operations to manage products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StoreService storeService;

    @ApiOperation(value = "Create a store")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = "/{storeId:\\d+}", method = RequestMethod.POST)
    public ResourceCreated<Long> addProduct(@PathVariable("storeId") Long storeId, @Validated @RequestBody ProductDTO product) {

        //check if store id exists
        try {
            StoreDTO store = storeService.getStoreById(storeId);

        } catch (Exception e){

            //TODO: throw exception if store does not exist
        }

        //Add storeId to product DTO
        product.setStoreId(storeId);

        //Add product under store
        return productService.addProduct(product);
    }

    @ApiOperation(value = "Get all products for a store ")
    @RequestMapping(value = "/{storeId:\\d+}", method = RequestMethod.GET)
    public List<ProductDTO> getProductsByStore(@PathVariable("storeId") Long storeId){
        return productService.getProductsByStore(storeId);
    }

    @ApiOperation(value = "Get product information by store id and product d")
    @RequestMapping(value= "/{storeId:\\d+}/{productId:\\d+}", method = RequestMethod.GET)
    public ProductDTO getProductByStoreIdProductId(@PathVariable("storeId") Long storeId, @PathVariable("productId") Long productId){
        return productService.getProductByStoreIdProductId(storeId, productId);
    }

    @ApiOperation(value = "Get product information by store Id and product name for a store Id")
    @RequestMapping(value = "/{storeId:\\d+}/{productName:[a-zA-Z]+}", method = RequestMethod.GET)
    public ProductDTO getProductByStoreIdProductName(@PathVariable("storeId") Long storeId, @PathVariable("productName") String productName){
        return productService.getProductByStoreIdProductName(storeId, productName);
    }

}


