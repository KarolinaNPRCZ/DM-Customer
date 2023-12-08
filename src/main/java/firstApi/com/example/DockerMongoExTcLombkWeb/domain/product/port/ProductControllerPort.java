package firstApi.com.example.DockerMongoExTcLombkWeb.domain.product.port;

import java.util.List;


public interface ProductControllerPort<RP, RQ> {


    public List<RP> findALL();
    public RP findById(int id);

    public List<RP> findAllByCategory(String category);

    public RP findByName(String name);

    public RP create(RP rq);

    public RP updateProductById(int id, RP rq);



}
