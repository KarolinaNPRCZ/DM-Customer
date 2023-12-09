package product.infrastructure;

public interface CustomProductRepository {
    void updateProductQuantity(String name, float newQuantity);
}
