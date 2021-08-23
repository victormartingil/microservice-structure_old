FROM openjdk:11-jdk-oracle
VOLUME /tmp
ADD target/microservice-a.jar microservice-a.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","microservice-a.jar"]