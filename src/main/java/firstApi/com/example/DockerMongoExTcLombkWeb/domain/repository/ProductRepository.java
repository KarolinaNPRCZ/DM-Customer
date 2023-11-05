package firstApi.com.example.DockerMongoExTcLombkWeb.domain.repository;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {
   @Query("{name:'?0'}")
   Product findItemByName(String name);

   @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
   List<Product> findAll(String category);

   public long count();
}
