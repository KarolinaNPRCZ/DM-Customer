package firstApi.com.example.DockerMongoExTcLombkWeb.domain.productsorder.core.model;

import product.domain.core.Product;

import java.time.LocalDateTime;
import java.util.List;

public class ProductOrder {
    private int id;
    private OrderId orderId;
    private List<Product> products;
    private OrderStatus status;
    private LocalDateTime deadline;

}
