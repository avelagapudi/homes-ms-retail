package com.tenx.ms.retail.controller;

import com.tenx.ms.retail.service.StoreService;
import com.tenx.ms.commons.rest.dto.ResourceCreated;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @ApiOperation(value = "Create a store")
//    @RequestMapping(method = RequestMethod.POST)
//    public StoreDTO addStore(@Validated @RequestBody StoreDTO store) {
//        return storeService.addStore(store);
//    }

    @ApiOperation(value = "Create a store")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResourceCreated<Long> addStore(@Validated @RequestBody StoreDTO store) {
        return storeService.addStore(store);
    }


    @ApiOperation(value = "Find all stores")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.GET)
    public List<StoreDTO> getAllStores() {
        return storeService.getAllStores();
    }


    @ApiOperation(value = "Find store by Id")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=404, message="Invalid Id Provided"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = "/{storeId:\\d+}", method = RequestMethod.GET)
    public StoreDTO getStoreById(@PathVariable("storeId") Long storeId) {
        return storeService.getStoreById(storeId);
    }


    @ApiOperation(value = "Find store by Name")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success"),
            @ApiResponse(code=404, message="Invalid Name Provided"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = "/{name:[a-zA-Z-_]+}", method = RequestMethod.GET)
    public StoreDTO getStoreByName(@PathVariable("name") String name) {
        return storeService.getStoreByName(name);
    }

}
