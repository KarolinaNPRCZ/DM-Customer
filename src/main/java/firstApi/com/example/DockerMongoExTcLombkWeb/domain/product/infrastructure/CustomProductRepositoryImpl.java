package firstApi.com.example.DockerMongoExTcLombkWeb.domain.product.infrastructure;

import com.mongodb.client.result.UpdateResult;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.product.domain.core.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


@Component
public class CustomProductRepositoryImpl implements CustomProductRepository{
    @Autowired
    MongoTemplate mongoTemplate;



    @Override
    public void updateProductQuantity(String name, float newQuantity) {
       Query query = new Query(Criteria.where("name").is(name));
        Update update = new Update();
        update.set("quantity", newQuantity);

        UpdateResult result = mongoTemplate.updateFirst(query, update, Product.class);

        if(result == null)
            System.out.println("No documents updated");
        else
            System.out.println(result.getModifiedCount() + " document(s) updated..");
    }
}
