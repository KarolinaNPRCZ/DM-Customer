package firstApi.com.example.DockerMongoExTcLombkWeb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public record ProductDTO(int id,String name,int quantity,String category){}

