package firstApi.com.example.DockerMongoExTcLombkWeb.domain;


import java.util.function.Function;

public class ProductDTOMapper implements Function<Product, ProductDTO>{
    public ProductDTO productToDTO(Product source)

    {
        return apply(source);
    }

    @Override
    public ProductDTO apply(Product source) {
        return new ProductDTO(source.getId(),source.getName(), source.getQuantity(), source.getCategory());


    }
}

