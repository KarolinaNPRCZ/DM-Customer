package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.product.ProductAlreadyExistsException;
import com.nprcz.dmcustomer.product.ProductDTO;
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
        verify(productDocumentMapper, times(1)).fromProductDTO(any());


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
}