package firstApi.com.example.DockerMongoExTcLombkWeb.domain.service;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.Product;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
@Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public Product findById(int id){
        if (productRepository.findById(id).isEmpty()){
            return null;
        }

        return productRepository.findById(id).orElseThrow(UnsupportedOperationException :: new);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAllByCategory(String category) {//in req for category we have null
        if (productRepository.findAll(category).isEmpty()){
            return null;
        }
        return productRepository.findAll(category);

    }
    public Product findByName(String name){
        if (productRepository.findProdutcsName(name).getName().isEmpty()){

            return null;
        }

        return productRepository.findProdutcsName(name);
    }

    public void update(int id, Product product) {//if put for id is "" in db create new prodct
        for (Product product1 : productRepository.findAll()){
            if (product1.getId() == id){
                if (product.getId() == 0){
                    product1.setId(id);
                }else {
                    product1.setId(product.getId());
                }
                product1.setQuantity(product.getQuantity());
                product1.setName(product.getName());
                product1.setCategory(product.getCategory());
            }
        }
    }
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
}
