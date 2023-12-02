package firstApi.com.example.DockerMongoExTcLombkWeb.domain.product.infrastructure;

public interface CustomProductRepository {
    void updateProductQuantity(String name, float newQuantity);
}
