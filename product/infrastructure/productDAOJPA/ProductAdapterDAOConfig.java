package firstApi.com.example.DockerMongoExTcLombkWeb.domain.product.infrastructure.productDAOJPA;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.product.port.ProductDAOPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.product.infrastructure.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
 class ProductAdapterDAOConfig {

     @Bean
     ProductDAOPort productDAOAdapter(final ProductRepository productRepository
             , final ProductDTOMapper productDTOMapper
             , final ProductEntityMapper productEntityMapper){
          return new ProductDAOAdapter(productRepository,productDTOMapper,productEntityMapper);
     }


}
