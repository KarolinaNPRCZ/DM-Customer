package firstApi.com.example.DockerMongoExTcLombkWeb;


import firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.UserConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@Import(UserConfig.class)
public class DockerMongoExTcLombkWebApplication  {
    public static void main(String[] args)  {
        SpringApplication.run(DockerMongoExTcLombkWebApplication.class, args);

    }
}
