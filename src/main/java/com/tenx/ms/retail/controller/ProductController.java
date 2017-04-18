package com.tenx.ms.retail.controller;

import com.tenx.ms.commons.rest.dto.ResourceCreated;
import com.tenx.ms.retail.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.tenx.ms.retail.dto.ProductDTO;
import com.tenx.ms.retail.service.StoreService;
import com.tenx.ms.retail.dto.StoreDTO;
import java.util.List;

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
    public ResponseEntity<?> addProduct(@PathVariable("storeId") Long storeId, @Validated @RequestBody ProductDTO product) {

        //check if store id exists
        StoreDTO store = storeService.getStoreById(storeId);
        if (store == null) {
            return new ResponseEntity<String>("Store does not exist", HttpStatus.NOT_FOUND);
        }

        //Add storeId to product DTO
        product.setStoreId(storeId);

        //Add product under store
        ProductDTO addedProduct = productService.addProduct(product);
        return new ResponseEntity<ProductDTO>(addedProduct, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all products for a store ")
    @RequestMapping(value = "/{storeId:\\d+}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> getProductsByStore(@PathVariable("storeId") Long storeId){
        List<ProductDTO> products = productService.getProductsByStore(storeId);

        if(products == null || products.isEmpty()) {
            return new ResponseEntity<List<ProductDTO>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.OK);
    }

    @ApiOperation(value = "Get product information by store id and product d")
    @RequestMapping(value= "/{storeId:\\d+}/{productId:\\d+}", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getProductByStoreIdProductId(@PathVariable("storeId") Long storeId, @PathVariable("productId") Long productId){
        ProductDTO product = productService.getProductByStoreIdProductId(storeId, productId);

        if(product == null) {
            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
    }

    @ApiOperation(value = "Get product information by store Id and product name for a store Id")
    @RequestMapping(value = "/{storeId:\\d+}/{productName:[a-zA-Z]+}", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getProductByStoreIdProductName(@PathVariable("storeId") Long storeId, @PathVariable("productName") String productName){
        ProductDTO product = productService.getProductByStoreIdProductName(storeId, productName);

        if(product == null) {
            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
    }

}


