package firstApi.com.example.DockerMongoExTcLombkWeb.domain.product.domain.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Product")
public class Product {
    @Id
    private int id;
    private double price;
    private String name;
    private int quantity;
    private ProductCategory category;


    public Product(int id,String name, int quantity, ProductCategory category){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
