package com.nprcz.dmcustomer.product;

import java.util.Comparator;

public class ProductComparator implements Comparator<ProductDTO> {


    @Override
    public int compare(ProductDTO o1, ProductDTO o2) {

        int productSKUId = o1.productSKUId().compareTo(o2.productSKUId());
        if (productSKUId != 0) {
            return productSKUId;
        }

        int nameComparison = o1.productName().compareToIgnoreCase(o2.productName());
        if (nameComparison != 0) {
            return nameComparison;
        }

        int productPrice = o1.productPrice().compareTo(o2.productPrice());
        if (productPrice != 0) {
            return productPrice;
        }

        int  productDescription = o1.productDescription().compareToIgnoreCase(o2.productDescription());
        if (productDescription != 0){
            return productDescription;
        }

        int result = o2.categories().stream()
                .mapToInt(s -> o2.categories().contains(s) ? o2.categories().get(o2.categories().indexOf(s)).compareTo(s) : 0)
                .sum();
        if (result != 0){
            return result;
        }



        return 0;
    }
}
