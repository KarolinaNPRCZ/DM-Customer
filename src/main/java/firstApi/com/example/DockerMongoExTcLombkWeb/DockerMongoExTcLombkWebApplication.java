package firstApi.com.example.DockerMongoExTcLombkWeb;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DockerMongoExTcLombkWebApplication implements CommandLineRunner {//potencjalny problem czy nie rozbic wywołań na 2 pliki
																				//póki co problemu nie ma a mamy połaczone dwie klasy :D
	@Autowired
	ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(DockerMongoExTcLombkWebApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

	}
}
