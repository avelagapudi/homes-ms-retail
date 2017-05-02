package com.tenx.ms.retail.service;

import com.tenx.ms.retail.entity.ProductEntity;
import com.tenx.ms.retail.dto.ProductDTO;
import com.tenx.ms.retail.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class productServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addProductTest(){
        ProductEntity product = new ProductEntity();
        product.setStoreId(1L);
        product.setName("testProduct");
        product.setDescription("test product description");
        product.setSku("23FC4");
        product.setPrice(1234.56);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setStoreId(1L);
        productDTO.setName("testProduct");
        productDTO.setDescription("test product description");
        productDTO.setSku("23FC4");
        productDTO.setPrice(1234.56);

        when(productRepository.save(any(ProductEntity.class))).thenReturn(product);
        ProductDTO result = productService.addProduct(productDTO);
        assertEquals(productDTO.getStoreId(), result.getStoreId());
        assertEquals(productDTO.getName(), result.getName());
        assertEquals(productDTO.getDescription(), result.getDescription());
        assertEquals(productDTO.getSku(), result.getSku());
        assertEquals(productDTO.getPrice(), result.getPrice());

    }

    @Test
    public void getProductsByStoreTest(){

        List<ProductEntity> products = Arrays.asList(
                new ProductEntity(1L,3L,"testProduct","test product description","3KY45",1234.56),
                new ProductEntity(1L,3L,"testSecondProduct", "test product description","45HYT",4567.78));

        List<ProductDTO> productDTOList = Arrays.asList(
                new ProductDTO(1L,3L,"testProduct","test product description","3KY45",1234.56),
                new ProductDTO(1L,3L,"testSecondProduct", "test product description","45HYT",4567.78)
        );

        when(productRepository.findByStoreId(3L)).thenReturn(products);

        List<ProductDTO> result = productService.getProductsByStore(3L);

        assertNotNull(result);
        assertEquals(productDTOList.size(), result.size());
    }

    @Test
    public void getProductByStoreIdProductIdTest(){
         ProductEntity product = new ProductEntity(1L,3L,"testProduct","test product description","3KY45",1234.56);
         ProductDTO productDTO = new ProductDTO(1L,3L,"testProduct","test product description","3KY45",1234.56);

         when(productRepository.findByStoreIdAndProductId(3L,1L)).thenReturn(product);

         ProductDTO result = productService.getProductByStoreIdProductId(3L, 1L);
         assertEquals(productDTO.getStoreId(), result.getStoreId());
         assertEquals(productDTO.getProductId(), result.getProductId());
         assertEquals(productDTO.getName(), result.getName());
         assertEquals(productDTO.getDescription(), result.getDescription());
         assertEquals(productDTO.getSku(), result.getSku());
         assertEquals(productDTO.getPrice(), result.getPrice());
    }

    @Test
    public void getProductByStoreIdProductNameTest(){
        ProductEntity product = new ProductEntity(1L,3L,"testProduct","test product description","3KY45",1234.56);
        ProductDTO productDTO = new ProductDTO(1L,3L,"testProduct","test product description","3KY45",1234.56);

        when(productRepository.findByStoreIdAndName(3L,"testProduct")).thenReturn(product);

        ProductDTO result = productService.getProductByStoreIdProductName(3L, "testProduct");
        assertEquals(productDTO.getStoreId(), result.getStoreId());
        assertEquals(productDTO.getProductId(), result.getProductId());
        assertEquals(productDTO.getName(), result.getName());
        assertEquals(productDTO.getDescription(), result.getDescription());
        assertEquals(productDTO.getSku(), result.getSku());
        assertEquals(productDTO.getPrice(), result.getPrice());
    }
}
