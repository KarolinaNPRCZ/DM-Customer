package firstApi.com.example.DockerMongoExTcLombkWeb;


import firstApi.com.example.DockerMongoExTcLombkWeb.application.userloginandsingup.UserLoginAndSignUpFacadeConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Import(UserLoginAndSignUpFacadeConfig.class)
public class DockerMongoExTcLombkWebApplication  {
    public static void main(String[] args)  {
        SpringApplication.run(DockerMongoExTcLombkWebApplication.class, args);

    }
}
