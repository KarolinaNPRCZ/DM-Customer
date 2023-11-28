package firstApi.com.example.DockerMongoExTcLombkWeb;


import firstApi.com.example.DockerMongoExTcLombkWeb.domain.Product;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.UserSession;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.repository.CustomProductRepository;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableMongoRepositories
public class DockerMongoExTcLombkWebApplication implements CommandLineRunner {//potencjalny problem czy nie rozbic wywołań na 2 pliki
    //póki co problemu nie ma a mamy połaczone dwie klasy :D
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomProductRepository customProductRepository;

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(DockerMongoExTcLombkWebApplication.class, args);

        UserSessionDAO userSessionDAO = new UserSessionDAOImpl();
        Optional<UserSession> userSession = userSessionDAO.getUserSessionById(2L);
        System.out.println(userSession);

    }

    @Override
    public void run(String... args) throws Exception {
        createProduct();//works
        showAllProduct();//works
        getProductByName("Works");

        getProductByCategory("Laptop");//works
        findCountOfProduct();//works
        updateCategoryName("Phone");//works

        updateProduct("Works",40);
     //   deleteProduct("Iph");

    }

    void createProduct() {//Create products
        System.out.println("Data creation started....");
      /*  productRepository.save(new Product("Iph", "65", 5, "Phone"));
        productRepository.save(new Product("Iphe", "Works", 55, "Phone"));
        productRepository.save(new Product("Dell", "Latidute", 4, "Laptop"));
        productRepository.save(new Product("Acer", "Aspire", 3, "Laptop"));
        productRepository.save(new Product("Iphone", "15", 5, "Phone"));
        productRepository.save(new Product("Samsung", "TAB7", 5, "Tablet"));*/
        System.out.println("Data creation complete");
    }
//show all data
    public void showAllProduct() {
        productRepository.findAll().forEach(product -> System.out.println(getItemDetails(product)));
    }

    //print item details
    public String getItemDetails(Product product) {
        if (product != null){
            System.out.println(
                    "Item Name: " + product.getName() +
                            ", \nItem Quantity: " + product.getQuantity() +
                            ", \nItem Category: " + product.getCategory()
            );

        }

        return "";
    }
   //serach product by name
    public void getProductByName(String name) {
        System.out.println("getting item by name: " + name);
        Product product = productRepository.findProdutcsName(name);
        System.out.println(getItemDetails(product));
    }
   //serach product by category
    public void getProductByCategory(String category) {
        System.out.println("getting item by category: " + category);
        List<Product> list = productRepository.findAll(category);
        list.forEach(product -> System.out.println("Name: " + product.getName() + ",Quantity: " + product.getQuantity()));

    }
    //numbers of products
    public void findCountOfProduct() {
        long count = productRepository.count();
        System.out.println("Number of documents in the collection: " + count);
    }
   //update category name using Mongo Repository
    public void updateCategoryName(String category){
        String newCategory = "Phones";


        List<Product> list = productRepository.findAll(category);
        list.forEach(product -> {
            product.setCategory(newCategory);
        });

        List<Product> productsCategoryUpdate =productRepository.saveAll(list);
        if (productsCategoryUpdate != null){
            System.out.println("successfully updated " + productsCategoryUpdate.size()+" products.");
        }
    }

    //update product MongoTemplate
    public void updateProduct(String name, float newQuantity){
        System.out.println("Aktualizacja ilości dla " + name);
        customProductRepository.updateProductQuantity(name,newQuantity);
    }
    //delete item
    public void deleteProduct(int id){
        productRepository.deleteById(id);
        System.out.println("Item with id " + id +" deleted...");;
    }
}
