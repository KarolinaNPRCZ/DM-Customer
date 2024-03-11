package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.product.ProductDTO;
import com.nprcz.dmcustomer.utils.productutils.SamplesProductsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        productDocument.setProductSKUId(1);
        ProductDTO productDTO = oneProductDocumentDTO();
        when(productDocumentMapper.mapToProductDocumentFromProductDTO(productDTO)).thenReturn(productDocument);
        when(productDocumentRepository.save(productDocument)).thenReturn(productDocument);

        //WHEN
        Integer productId = productDAOImpl.save(productDTO);
        //THEN
        assertEquals(1,productId);
        verify(productDocumentMapper,times(1)).mapToProductDocumentFromProductDTO(productDTO);
        verify(productDocumentRepository,times(1)).save(productDocument);

    }
}