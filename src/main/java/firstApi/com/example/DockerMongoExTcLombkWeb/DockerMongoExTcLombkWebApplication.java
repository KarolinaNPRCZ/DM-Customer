package firstApi.com.example.DockerMongoExTcLombkWeb;


import firstApi.com.example.DockerMongoExTcLombkWeb.application.UserLoginAndSignUpFacadeConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@Import(UserLoginAndSignUpFacadeConfig.class)
public class DockerMongoExTcLombkWebApplication  {
    public static void main(String[] args)  {
        SpringApplication.run(DockerMongoExTcLombkWebApplication.class, args);

    }
}
