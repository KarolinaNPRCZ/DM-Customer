package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.product.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductDAOImplTest {
    @Mock
    private ProductDocumentMapper prodctDocumentMapper;

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
        //given
        List<String> testCategories = new ArrayList<>();
        testCategories.add("home");
        testCategories.add("office");
        ProductDocument productDocument = new ProductDocument();
        productDocument.setProductId(1);
        ProductDTO productDTO = ProductDTO.builder()
                .productId(1)
                .productName("papper")
                .productPrice(14.10)
                .productDescription("best papper on the world")
                .categories(testCategories)
                .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now()).build();


        when(prodctDocumentMapper.mapToProductDocumentFromProductDTO(productDTO)).thenReturn(productDocument);

        when(productDocumentRepository.save(productDocument)).thenReturn(productDocument);


        Integer productId = productDAOImpl.save(productDTO);

        assertEquals(1,productId);
        verify(prodctDocumentMapper,times(1)).mapToProductDocumentFromProductDTO(productDTO);
        verify(productDocumentRepository,times(1)).save(productDocument);

    }
}