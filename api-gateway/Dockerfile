FROM openjdk:11-jdk-oracle
VOLUME /tmp
ADD target/api-gateway.jar api-gateway.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","api-gateway.jar"]