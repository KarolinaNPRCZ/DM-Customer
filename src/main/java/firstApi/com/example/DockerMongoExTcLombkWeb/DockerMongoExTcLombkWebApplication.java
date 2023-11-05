package firstApi.com.example.DockerMongoExTcLombkWeb;


import firstApi.com.example.DockerMongoExTcLombkWeb.domain.Product;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DockerMongoExTcLombkWebApplication implements CommandLineRunner {//potencjalny problem czy nie rozbic wywołań na 2 pliki
    //póki co problemu nie ma a mamy połaczone dwie klasy :D
    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(DockerMongoExTcLombkWebApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        createProduct();
        showAllProduct();
        //getProductByName("XS");
        //getProductByCategory("Phone");
        findCountOfProduct();
        //updateCategoryName("Phone");

    }

    void createProduct() {//utworzenie kilku produktów
        System.out.println("Data creation started....");
        productRepository.save(new Product("Iphone", "XS", 5, "Phone"));
        productRepository.save(new Product("Dell", "Latidute", 4, "Laptop"));
        productRepository.save(new Product("Acer", "Aspire", 3, "Laptop"));
        productRepository.save(new Product("Iphone", "15", 5, "Phone"));
        productRepository.save(new Product("Samsung", "TAB7", 5, "Tablet"));
        System.out.println("Data creation complete");
    }

    public void showAllProduct() {
        productRepository.findAll().forEach(product -> System.out.println(getItemDetails(product)));
    }

    public String getItemDetails(Product product) {
        String responseString = "Product Name: " + product.getName() + ", \nQuantity: " + product.getQunatity() + ", \nProduct Category: " + product.getCategory();
        return responseString;

    }

    public void getProductByName(String name) {
        System.out.println("getting item by name: " + name);
        Product item = productRepository.findItemByName(name);
        System.out.println(getItemDetails(item));
    }

    public void getProductByCategory(String category) {
        System.out.println("getting item by category: " + category);
        List<Product> list = productRepository.findAll(category);
        list.forEach(product -> System.out.println("Name: " + product.getName() + ",Quantity: " + product.getQunatity()));

    }

    public void findCountOfProduct() {
        long count = productRepository.count();
        System.out.println("Number of documents in the collection: " + count);
    }

    public void updateCategoryName(String category){
        String newCategory = "Phones";
        List<Product> list = productRepository.findAll(category);
        list.forEach(product -> {
            product.setCategory(newCategory);
        });
        List<Product> productsCategoryUpdate =productRepository.saveAll(list);
        if (productsCategoryUpdate!= null){
            System.out.println("successfully updated " + productsCategoryUpdate.size()+" products.");
        }
    }

    public void deleteProduct(String id){
        productRepository.deleteById(id);
        System.out.println("Item with id " + id +" deleted...");;
    }
}
