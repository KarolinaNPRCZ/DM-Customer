FROM eclipse-temurin:17-jre-alpine

COPY ./target/application-1.0.jar ./application-1.0.jar

ENTRYPOINT ["java", "-jar", "/application-1.0.jar"]