package com.tenx.ms.retail.service;

import com.tenx.ms.commons.rest.dto.ResourceCreated;
import org.springframework.stereotype.Service;
import com.tenx.ms.retail.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.tenx.ms.retail.dto.ProductDTO;
import com.tenx.ms.retail.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductDTO addProduct(ProductDTO product){
        ProductEntity productEntity = new ProductEntity();

        productEntity.setStoreId(product.getStoreId());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setSku(product.getSku());
        productEntity.setPrice(product.getPrice());

        productEntity = productRepository.save(productEntity);

        //return new ResourceCreated<>(productEntity.getProductId());
        return convertToDTO(productEntity);

    }

    public List<ProductDTO> getProductsByStore(Long storeId) {
        List<ProductEntity> products = productRepository.findByStoreId(storeId);

        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductByStoreIdProductId(Long storeId, Long productId) {
        ProductEntity result = productRepository.findByStoreIdAndProductId(storeId, productId);
        return convertToDTO(result);
    }

    public ProductDTO getProductByStoreIdProductName(Long storeId, String productName){
        ProductEntity result = productRepository.findByStoreIdAndName(storeId, productName);
        return convertToDTO(result);
    }

    public ProductDTO convertToDTO(ProductEntity productEntity) {
        ProductDTO dto = new ProductDTO();

        dto.setProductId(productEntity.getProductId());
        dto.setStoreId(productEntity.getStoreId());
        dto.setName(productEntity.getName());
        dto.setDescription(productEntity.getDescription());
        dto.setSku(productEntity.getSku());
        dto.setPrice(productEntity.getPrice());

        return dto;
    }
}
