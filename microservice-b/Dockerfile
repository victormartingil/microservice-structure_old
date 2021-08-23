FROM openjdk:11-jdk-oracle
VOLUME /tmp
ADD target/microservice-b.jar microservice-b.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","microservice-b.jar"]