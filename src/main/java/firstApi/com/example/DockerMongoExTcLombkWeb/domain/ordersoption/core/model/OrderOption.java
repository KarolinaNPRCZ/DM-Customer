package firstApi.com.example.DockerMongoExTcLombkWeb.domain.ordersoption.core.model;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.productsorder.core.model.ProductOrder;

import java.util.List;

public class OrderOption {
    private int id;
    private String name;
    private List<ProductOrder> options;
}
