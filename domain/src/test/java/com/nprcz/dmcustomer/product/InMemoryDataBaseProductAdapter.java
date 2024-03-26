package com.nprcz.dmcustomer.product;

import com.nprcz.dmcustomer.ports.in.product.ProductDAOPort;

import java.util.*;
import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

class InMemoryDataBaseProductAdapter implements ProductDAOPort {
     private final Map<Integer,ProductDTO> productsDTOMap = new HashMap<>();

    @Override
    public Integer save(ProductDTO productDTO) {
        if (productDTO.productSKUId() == null){
            productDTO = productDTO.toBuilder().productSKUId(1).build();
        }
        if (productsDTOMap.containsKey(productDTO.productSKUId())) throw new ProductAlreadyExistsException(productDTO.productSKUId());
        productsDTOMap.put(productDTO.productSKUId(),productDTO);
        return productDTO.productSKUId();

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

}
