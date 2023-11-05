package firstApi.com.example.DockerMongoExTcLombkWeb.domain.repository;

public interface CustomProductRepository {
    void updateProductQuantity(String name, float newQuantity);
}
