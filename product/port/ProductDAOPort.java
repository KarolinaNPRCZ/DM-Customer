package firstApi.com.example.DockerMongoExTcLombkWeb.domain.product.port;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.product.infrastructure.productDAOJPA.ProductDTO;

import java.util.List;

public interface ProductDAOPort {

     List<ProductDTO> findAll();

     ProductDTO findById(int id);

     ProductDTO save(ProductDTO product);

     List<ProductDTO> findAllByCategory(String category);

     ProductDTO findByName(String name);

     void update(int id, ProductDTO product);

     void deleteById(int id);
}
