package product.infrastructure.productDAOJPA;

import product.domain.core.ProductCategory;


public record ProductDTO(int id, String name, int quantity, ProductCategory category){}

