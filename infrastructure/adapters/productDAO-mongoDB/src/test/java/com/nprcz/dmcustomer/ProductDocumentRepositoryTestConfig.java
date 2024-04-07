package com.nprcz.dmcustomer;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@EnableMongoAuditing
@Configuration
@EntityScan("com.nprcz.dmcustomer")
class ProductDocumentRepositoryTestConfig {


}