package firstApi.com.example.DockerMongoExTcLombkWeb.application.rest;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.Product;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.service.ProductService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findALL() {
       // productRepository.findAll().forEach(product -> System.out.println(getItemDetails(product)));
    return productService.findAll();

    }

      @GetMapping("/{id}")
      public Product findById(@PathVariable String id){
          return productService.findById(id);
      }
    @GetMapping("/search/category/{category}")
    public List<Product> findAllByCategory(@PathVariable String category){
        return productService.findAllByCategory(category);
    }
    @GetMapping("/search/name/{name}")
    public Product findByName(@PathVariable String name){
        return productService.findByName(name);
    }
    @PostMapping("/newproduct")
    public Product create(@RequestBody Product product){
        return productService.save(product);
    }
    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable String id,@RequestBody Product product){
      productService.update(id, product);

       return productService.save(product);

    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        productService.deleteById(id);
    }


}
