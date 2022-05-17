FROM maven:latest as base

WORKDIR /app

COPY pom.xml .
RUN mvn -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
COPY src src

FROM base as test
RUN mvn test

FROM base as development
CMD ["mvn", "spring-boot:run", \ 
    "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]

FROM test as build
RUN mvn package

FROM openjdk:11-jre-slim as production
COPY --from=build /app/target/book-management-*.jar /book-management.jar
EXPOSE 8080

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/book-management.jar"]