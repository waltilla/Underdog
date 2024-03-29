FROM azul/zulu-openjdk:17-latest AS builder
RUN apt-get update && apt-get install -y maven
RUN mkdir /maven
COPY pom.xml /maven/pom.xml
COPY src /maven/src
RUN cd /maven && \
    mvn clean package -DskipTests

FROM azul/zulu-openjdk:17-latest
COPY --from=builder /maven/target/underdog-*.jar /service/underdog.jar
WORKDIR /service
EXPOSE 8080
CMD ["java", "-jar", "/service/underdog.jar", "--server.address=0.0.0.0"]