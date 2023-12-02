package firstApi.com.example.DockerMongoExTcLombkWeb;

public interface GenericConverter<E,D> {

    E createFromDTO(D dto);



    E updateEntity(D dto);


}