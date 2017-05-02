package com.tenx.ms.retail.controller;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/v1/products")
@Api(value="/products", description="operations to manage products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @Autowired
    private StoreService storeService;

    @ApiOperation(value = "Add a product")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=412, message="Precondition failure"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = "/{storeId:\\d+}", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@PathVariable("storeId") Long storeId, @Validated @RequestBody ProductDTO product) {

        //check if store id exists
        logger.debug("Check if store exists by store Id: ", storeId);
        StoreDTO store = storeService.getStoreById(storeId);
        if (store == null) {
            logger.debug("Store does not exist");
            return new ResponseEntity<String>("Store does not exist", HttpStatus.NOT_FOUND);
        }

        //Add storeId to product DTO
        product.setStoreId(storeId);

        //Add product under store
        logger.debug("Add Product", product);
        ProductDTO addedProduct = productService.addProduct(product);
        return new ResponseEntity<ProductDTO>(addedProduct, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Get all products for a store ")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = "/{storeId:\\d+}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> getProductsByStore(@PathVariable("storeId") Long storeId){
        logger.debug("Get all products by store id", storeId);
        List<ProductDTO> products = productService.getProductsByStore(storeId);

        if(products == null || products.isEmpty()) {
            logger.debug("products does not xist for the store");
            return new ResponseEntity<List<ProductDTO>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.OK);
    }


    @ApiOperation(value = "Get product information by store id and product d")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value= "/{storeId:\\d+}/{productId:\\d+}", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getProductByStoreIdProductId(@PathVariable("storeId") Long storeId, @PathVariable("productId") Long productId){
        logger.debug("Get product by storeId and productId");
        ProductDTO product = productService.getProductByStoreIdProductId(storeId, productId);

        if(product == null) {
            logger.debug("product not found");
            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
    }


    @ApiOperation(value = "Get product information by store Id and product name for a store Id")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = "/{storeId:\\d+}/{productName:[a-zA-Z]+}", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getProductByStoreIdProductName(@PathVariable("storeId") Long storeId, @PathVariable("productName") String productName){
        logger.debug("Get product by storeId and product name");
        ProductDTO product = productService.getProductByStoreIdProductName(storeId, productName);

        if(product == null) {
            logger.debug("product not found");
            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
    }

}


