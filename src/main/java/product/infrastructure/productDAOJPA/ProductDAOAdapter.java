package product.infrastructure.productDAOJPA;

import product.port.ProductDAOPort;
import product.infrastructure.ProductRepository;

import java.util.List;
public class ProductDAOAdapter implements ProductDAOPort {

    final ProductRepository productRepository;
    final ProductDTOMapper productDTOMapper;
    final ProductEntityMapper productEntityMapper;
    public ProductDAOAdapter(ProductRepository productRepository, ProductDTOMapper productDTOMapper, ProductEntityMapper productEntityMapper) {

        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
        this.productEntityMapper = productEntityMapper;

    }
    public List<ProductDTO> findAll() {
        return null;
    }
    public ProductDTO findById(int id) {
        return null;
    }
    public ProductDTO save(ProductDTO product) {
        return null;
    }
    public List<ProductDTO> findAllByCategory(String category) {
        return null;
    }
    public ProductDTO findByName(String name) {
        return null;
    }

    public void update(int id, ProductDTO product) {

    }

    public void deleteById(int id) {

    }
}
