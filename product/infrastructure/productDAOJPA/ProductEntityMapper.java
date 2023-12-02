package firstApi.com.example.DockerMongoExTcLombkWeb.domain.product.infrastructure.productDAOJPA;


import firstApi.com.example.DockerMongoExTcLombkWeb.domain.product.domain.core.Product;

import java.util.function.Function;

public class ProductEntityMapper implements Function<ProductDTO, Product>{
    public Product productToDTO(ProductDTO source)

    {
        return apply(source);
    }

    @Override
    public Product apply(ProductDTO source) {
        return new Product(source.id(),source.name(), source.quantity(), source.category());


    }
}

