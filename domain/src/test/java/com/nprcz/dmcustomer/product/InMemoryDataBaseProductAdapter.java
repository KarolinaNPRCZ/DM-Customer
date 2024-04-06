package com.nprcz.dmcustomer.product;

import com.nprcz.dmcustomer.ports.in.product.ProductDAOPort;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

class InMemoryDataBaseProductAdapter implements ProductDAOPort {
     private final Map<Integer,ProductDTO> productsDTOMap = new HashMap<>();

    @Override
    public String save(ProductDTO productDTO) {
        if (productDTO.productSKUId() == null){
            productDTO = productDTO.toBuilder().productSKUId(1).build();
        }
        if (productsDTOMap.containsKey(productDTO.productSKUId())) throw new ProductAlreadyExistsException(productDTO.productSKUId());
        productsDTOMap.put(productDTO.productSKUId(),productDTO);
        return productDTO.id();

    }

    @Override
    public Integer deleteProduct(ProductDTO productDTO) {
      return 1;
    }

    @Override
    public Optional<ProductDTO> findProductBySKUId(Integer SKU) {
        return productsDTOMap.values()
                .stream()
                .filter(productDTO -> productDTO.productSKUId().equals(SKU))
                .findFirst();
    }

    @Override
    public List<ProductDTO> findProductsByName(String productName) {
        List<ProductDTO> foundProductDTOs = new ArrayList<>();
        for (Map.Entry<Integer, ProductDTO> productDTO : productsDTOMap.entrySet())

        {
           if (containsIgnoreCase(productDTO.getValue().productName(),productName)){
               foundProductDTOs.add(productDTO.getValue());
           }
        }


        return foundProductDTOs;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return new ArrayList<>(productsDTOMap.values());
    }

    @Override
    public Optional<ProductDTO> updateProductQuantityBySKUId(Integer productSKUId, Integer newQuantity) {

        ProductDTO productDTO = productsDTOMap.get(productSKUId);
        if (productDTO == null) throw new ProductNotFoundException(productSKUId);

        ProductDTO  updatedProductDTO = productDTO.toBuilder().productQuantity(productDTO.productQuantity() + newQuantity).build();
        productsDTOMap.put(productSKUId,updatedProductDTO);

        return Optional.of(updatedProductDTO);
    }

}
