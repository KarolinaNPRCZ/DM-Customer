package firstApi.com.example.DockerMongoExTcLombkWeb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Product")
public class Product {

    @Id
    private String id;
    private String name;
    private int qunatity;
    private String category;

    public Product(String id, String name, int qunatity,String category){
        super();
        this.id = id;
        this.name = name;
        this.qunatity = qunatity;
        this.category = category;
    }
}
