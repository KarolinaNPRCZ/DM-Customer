package com.nprcz.dmcustomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(classes = DockerMongoExTcLombkWebApplication.class)
@ActiveProfiles("integration")
@AutoConfigureMockMvc
@Testcontainers
@ContextConfiguration
public class AbstractIntegrationTests {


    @Container
    protected static PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>(
                    DockerImageName.parse("postgres:15.2")
            ).withInitScript("init-test-data.sql");

    @Container
    protected static MongoDBContainer mongoDbContainer =
            new MongoDBContainer(
                    DockerImageName.parse("mongo:4.0.10")
            );

    @RegisterExtension
    protected static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().dynamicPort())
            .build();

    @DynamicPropertySource
    private static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.data.mongodb.uri",mongoDbContainer::getReplicaSetUrl);
    }

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

}