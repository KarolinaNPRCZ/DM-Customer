package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.product.InvalidProductQuantityException;
import com.nprcz.dmcustomer.product.ProductAlreadyExistsException;
import com.nprcz.dmcustomer.product.ProductDTO;
import com.nprcz.dmcustomer.product.ProductNotFoundException;
import com.nprcz.dmcustomer.utils.productutils.SamplesProductsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductDAOImplTest implements SamplesProductsResponse {
    @Mock
    private ProductDocumentMapper productDocumentMapper;

    @Mock
    private ProductDocumentRepository productDocumentRepository;

    @InjectMocks
    private ProductDAOImpl productDAOImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_successfully_save_product_and_return_product_id(){
        //GIVEN
        ProductDocument productDocument = new ProductDocument();
        productDocument.setId(UUID.randomUUID().toString());
        ProductDTO productDTO = oneProductDocumentDTO();
        when(productDocumentMapper.fromProductDTO(productDTO)).thenReturn(productDocument);
        when(productDocumentRepository.save(productDocument)).thenReturn(productDocument);

        //WHEN
        String productId = productDAOImpl.save(productDTO);
        //THEN
        assertEquals(productDocument.id ,productId);
        verify(productDocumentMapper,times(1)).fromProductDTO(productDTO);
        verify(productDocumentRepository,times(1)).save(productDocument);

    }

    @Test
    void should_throw_ProductAlreadyExistsException() {
        // GIVEN
        ProductDTO productDTO = oneProductDocumentDTO();
        ProductDocument productDocument = new ProductDocument();
        when(productDocumentMapper.fromProductDTO(productDTO))
                .thenReturn(productDocument);
        when(productDocumentRepository.save(productDocument))
                .thenThrow(DuplicateKeyException.class);

        // WHEN && THEN
        assertThrows(
                ProductAlreadyExistsException.class,
                () -> productDAOImpl.save(productDTO)
        );
        verify(productDocumentMapper, times(1))
                .fromProductDTO(productDTO);
        verify(productDocumentRepository, times(1))
                .save(productDocument);
    }

    @Test
    void should_successfully_find_product_by_findProductBySKUId() {
        //GIVEN
        ProductDTO productDTO = oneProductDocumentDTO();
        ProductDocument product = new ProductDocument();
        when(productDocumentRepository.getProductDocumentByProductSKUId(productDTO.productSKUId()))
                .thenReturn(Optional.of(product));
        when(productDocumentMapper.apply(product))
                .thenReturn(productDTO);
        // WHEN
        Optional<ProductDTO> result = productDAOImpl.findProductBySKUId(productDTO.productSKUId());

        // THEN
        assertTrue(result.isPresent());
        assertEquals(productDTO, result.get());
        verify(productDocumentRepository, times(productDTO.productSKUId())).getProductDocumentByProductSKUId(1);

    }

    @Test
    public void should_successfully_find_product_by_findProductBySKUId_ProductNotExists_ReturnEmptyOptional() {
        // GIVEN
        Integer nonExistsSKU =696969;
        when(productDocumentRepository.getProductDocumentByProductSKUId(nonExistsSKU))
                .thenReturn(Optional.empty());

        // WHEN
        Optional<ProductDTO> result = productDAOImpl.findProductBySKUId(nonExistsSKU);

        // THEN
        assertFalse(result.isPresent());
        verify(productDocumentRepository, times(1))
                .getProductDocumentByProductSKUId(nonExistsSKU);
        verify(productDocumentMapper, never()).apply(any());
    }

    @Test
    void should_successfully_deleteProduct() {
        //GIVEN
        ProductDTO productDTO = oneProductDocumentDTO();
        ProductDocument productDocument = new ProductDocument()
                .toBuilder()
                .productSKUId(productDTO.productSKUId())
                .build();


        when(productDocumentMapper
                .fromProductDTO(productDTO))
                .thenReturn(productDocument);

        //WHEN
         productDAOImpl.deleteProduct(productDTO);
        //THEN
        verify(productDocumentRepository, times(1)).deleteByProductSKUId(productDTO.productSKUId());



    }

    @Test
    void should_successfully_findProductsByName() {

        ProductDocument productDocument = new ProductDocument().toBuilder().productName("A4 copy papper").build();
        List<ProductDocument> listOfProductDocument = new ArrayList<>();
        listOfProductDocument.add(productDocument);
        listOfProductDocument.add(productDocument);
        //GIVEN
        when(productDocumentRepository
                .getProductDocumentsByProductNameContainsIgnoreCase("COPY"))
                .thenReturn(listOfProductDocument);
        when(productDocumentMapper.apply(productDocument)).thenReturn(oneProductDocumentDTO());
        //WHEN
        List<ProductDTO> result = productDAOImpl.findProductsByName("COPY");
        //THEN
        assertFalse(result.isEmpty());
        assertEquals(listOfProductDocument.size(), result.size());
        verify(productDocumentRepository, times(1)).getProductDocumentsByProductNameContainsIgnoreCase("COPY");
        verify(productDocumentMapper, times(result.size())).apply(any());

    }

    @Test
    void should_successfully_getAllProducts() {
        //GIVEN
        ProductDocument productDocument = new ProductDocument();
        List<ProductDocument> listOfProductDocument = new ArrayList<>();
        listOfProductDocument.add(productDocument);
        listOfProductDocument.add(productDocument);
        listOfProductDocument.add(productDocument);
        when(productDocumentRepository.findAll()).thenReturn(listOfProductDocument);
        when(productDocumentMapper.apply(productDocument)).thenReturn(oneProductDocumentDTO());
        //WHEN
        List<ProductDTO> result = productDAOImpl.getAllProducts();
        //THEN
        assertFalse(result.isEmpty());
        assertEquals(listOfProductDocument.size(), result.size());
        verify(productDocumentRepository, times(1)).findAll();
        verify(productDocumentMapper, times(result.size())).apply(any());
    }

    @Test
    void should_successfully_updated_quantity_updateQuantity() {
        //GIVEN
        int productQuantity = 5;
        int productQuantityBeforeUpdate =1;
        ProductDTO productDTO = oneProductDocumentDTO().toBuilder().productQuantity(productQuantityBeforeUpdate + productQuantity).build();
        ProductDocument productDocumentBeforeUpdate =  ProductDocument.builder()
                .productQuantity(productQuantityBeforeUpdate)
                .productSKUId(productDTO.productSKUId())
                .build();
        when(productDocumentRepository.getProductDocumentByProductSKUId(any()))
                .thenReturn(Optional.of(productDocumentBeforeUpdate));
        when(productDocumentRepository.save(any())).thenReturn(productDocumentBeforeUpdate);
        when(productDocumentMapper.mapToProductDTOFrom(any())).thenReturn(productDTO);

        //WHEN
        Optional<ProductDTO> productDTOResponse = productDAOImpl.updateProductQuantityBySKUId(1,productQuantity);
        //THEN
        assertThat(productDTOResponse.map(ProductDTO::productQuantity).orElseThrow()).isGreaterThan(productQuantityBeforeUpdate);
        verify(productDocumentRepository,times(1)).save(productDocumentBeforeUpdate);
        verify(productDocumentRepository,times(1)).getProductDocumentByProductSKUId(1);
    }

    @Test
    void should_return_Product_not_found_exception_updateQuantity() {
        //GIVEN
       ProductDocument productDocumentBeforeUpdate =  ProductDocument.builder()
                .productQuantity(5)
                .productSKUId(3)
                .build();
        when(productDocumentRepository.getProductDocumentByProductSKUId(any())).thenThrow(new ProductNotFoundException(productDocumentBeforeUpdate.productSKUId));
        //WHEN && THEN
        assertThrows(
                ProductNotFoundException.class,
                () -> productDAOImpl.updateProductQuantityBySKUId(1,any())
        );
    }
    @Test
    void should_return_InvalidProductQuantityException_updateQuantity() {
        //GIVEN
        ProductDocument productDocumentBeforeUpdate =  ProductDocument.builder()
                .productQuantity(5)
                .productSKUId(3)
                .build();
        when(productDocumentRepository.getProductDocumentByProductSKUId(3)).thenReturn(Optional.ofNullable(productDocumentBeforeUpdate));
        //WHEN && THEN
        assertThrows(
                InvalidProductQuantityException.class,
                () -> productDAOImpl.updateProductQuantityBySKUId(3,-10)
        );
    }
}