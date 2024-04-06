package com.nprcz.dmcustomer.product;

import com.nprcz.dmcustomer.utils.productutils.SamplesProductsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductManagementFacadeTest implements SamplesProductsResponse {

    private final InMemoryDataBaseProductAdapter inMemoryDataBaseProductAdapter = new InMemoryDataBaseProductAdapter();
    private final ProductManagementFacade productManagementFacade = new ProductManagementFacade(inMemoryDataBaseProductAdapter);
    private final List<ProductDTO> productDTOs = threeProductDocumentDTOs();

    @BeforeEach
    void setUp() {

        List<String> UUID =
                productDTOs.stream()
                        .map(productManagementFacade::createProduct)
                        .toList();

    }

    @Test
    void should_successfully_createProduct_and_return_ProductBySKUId() {
        //GIVEN
        ProductDTO productDTO = oneProductDocumentDTO().toBuilder().productSKUId(19).productQuantity(5).build();
        productDTO.toBuilder().productSKUId(19).build();
        //WHEN
        String UUID = productManagementFacade.createProduct(productDTO);
        ProductDTO recivedProductDTO = productManagementFacade.getProductBySKUId(productDTO.productSKUId());
        //THEN
        assertEquals(0,
                new ProductComparator().compare(
                        productDTO,
                        recivedProductDTO
                ));
        assertEquals(UUID,recivedProductDTO.id());
    }

    @Test
    void should_successfully_return_ProductsByName() {
        //GIVEN && WHEN
        List<ProductDTO> receivedProductsDTO = productManagementFacade.getProductsByName("paper");
        //THEN
        assertTrue(receivedProductsDTO.size() > 2);

    }

    @Test
    void should_successfully_return_AllProducts() {
        //GIVEN && WHEN
        List<ProductDTO> receivedProductsDTO = productManagementFacade.getAllProducts();
        //THEN
        assertThat(receivedProductsDTO).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(productDTOs);
    }

    @Test
    void should_throw_ProductNotFoundException_getProductBySKUId() {
        //GIVEN && WHEN && THEN
        Assertions.assertThrows(ProductNotFoundException.class,
                () -> productManagementFacade.getProductBySKUId(999999999));
    }

    @Test
    void should_throw_ProductAlreadyExistsException_createProduct() {
        //GIVEN
        ProductDTO productDTO = twoProductDocumentDTO();
        productManagementFacade.createProduct(productDTO);
        // WHEN && THEN
        Assertions.assertThrows(ProductAlreadyExistsException.class,
                () -> productManagementFacade.createProduct(productDTO));
    }

    @Test
    void should_successfully_update_quantity_for_product() {
        //GIVEN
        int quantityBeforeUpdate = 5;
        ProductDTO productDTO = oneProductDocumentDTO().toBuilder()
                .productSKUId(19)
                .productQuantity(quantityBeforeUpdate)
                .build();
        String UUID = productManagementFacade.createProduct(productDTO);

        //WHEN
        ProductDTO recivedProduct = productManagementFacade.updateProductQuantityBySKUId(productDTO.productSKUId(),5);
        //THEN
        assertTrue(recivedProduct.productQuantity() > quantityBeforeUpdate);
    }

    @Test
    void should_throw_ProductNotFoundException_updateQuantityBySKUId() {
        //GIVEN && WHEN && THEN
        Assertions.assertThrows(ProductNotFoundException.class,
                () -> productManagementFacade.updateProductQuantityBySKUId(999999999,67));
    }



}