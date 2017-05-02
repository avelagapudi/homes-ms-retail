package com.tenx.ms.retail.controller;

import com.tenx.ms.retail.service.StoreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.tenx.ms.retail.dto.StoreDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/v1/stores")
@Api(value="/stores", description="operations to manage stores")
public class StoreController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StoreService storeService;

    @ApiOperation(value = "Create a store")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=412, message="Precondition failure"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addStore(@Validated @RequestBody StoreDTO store) {

        StoreDTO existingStore = storeService.getStoreByName(store.getStoreName());

        if (existingStore == null )
        {
            logger.debug("Add New Store", store);
            StoreDTO addedStore = storeService.addStore(store);
            return new ResponseEntity<StoreDTO>(addedStore,HttpStatus.OK);

        } else {
            logger.debug("Store name already exists");
            return new ResponseEntity<String>("Store name already exits",HttpStatus.CONFLICT);
        }

    }


    @ApiOperation(value = "Find all stores")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        logger.debug("Get list of all the stores");
        List<StoreDTO> stores = storeService.getAllStores();

        if(stores == null || stores.isEmpty()) {
            return new ResponseEntity<List<StoreDTO>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<StoreDTO>>(stores, HttpStatus.OK);
    }


    @ApiOperation(value = "Find store by Id")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = "/{storeId:\\d+}", method = RequestMethod.GET)
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable("storeId") Long storeId) {
        logger.debug("Get store by id");
        StoreDTO store = storeService.getStoreById(storeId);

        if(store == null) {
            logger.debug("Store not found");
            return new ResponseEntity<StoreDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<StoreDTO>(store, HttpStatus.OK);
    }


    @ApiOperation(value = "Find store by Name")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = "/{name:[a-zA-Z]+}", method = RequestMethod.GET)
    public ResponseEntity<StoreDTO> getStoreByName(@PathVariable("name") String name) {
        logger.debug("Get store by name");
        StoreDTO store = storeService.getStoreByName(name);

        if(store == null) {
            logger.debug("Store not found");
            return new ResponseEntity<StoreDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<StoreDTO>(store, HttpStatus.OK);
    }

}
