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

/**
 * Created by anupamav on 3/21/17.
 */
@RestController
@RequestMapping("/v1/stores")
@Api(value="/stores", description="operations to manage stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @ApiOperation(value = "Create a store")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<StoreDTO> addStore(@Validated @RequestBody StoreDTO store) {

        StoreDTO existingStore = storeService.getStoreByName(store.getStoreName());

        if (existingStore == null )
        {
            StoreDTO addedStore = storeService.addStore(store);
            return new ResponseEntity<StoreDTO>(addedStore,HttpStatus.CREATED);

        } else {
            return new ResponseEntity<StoreDTO>(HttpStatus.CONFLICT);
        }

    }


    @ApiOperation(value = "Find all stores")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StoreDTO>> getAllStores() {

        List<StoreDTO> stores = storeService.getAllStores();

        if(stores == null || stores.isEmpty()) {
            return new ResponseEntity<List<StoreDTO>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<StoreDTO>>(stores, HttpStatus.OK);
    }


    @ApiOperation(value = "Find store by Id")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=404, message="Invalid Id Provided"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = "/{storeId:\\d+}", method = RequestMethod.GET)
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable("storeId") Long storeId) {
        StoreDTO store = storeService.getStoreById(storeId);

        if(store == null) {
            return new ResponseEntity<StoreDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<StoreDTO>(store, HttpStatus.OK);
    }


    @ApiOperation(value = "Find store by Name")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=404, message="Invalid Name Provided"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = "/{name:[a-zA-Z]+}", method = RequestMethod.GET)
    public ResponseEntity<StoreDTO> getStoreByName(@PathVariable("name") String name) {


        StoreDTO store = storeService.getStoreByName(name);

        if(store == null) {
            return new ResponseEntity<StoreDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<StoreDTO>(store, HttpStatus.OK);
    }

}
