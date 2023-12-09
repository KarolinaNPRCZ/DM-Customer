package product.infrastructure.productDAOJPA;

import product.port.ProductDAOPort;
import product.infrastructure.ProductRepository;
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
