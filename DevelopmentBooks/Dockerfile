FROM openjdk:20-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/DevelopmentBooks-0.0.1-SNAPSHOT.jar DevelopmentBooks.jar
ENTRYPOINT ["java", "-jar", "/DevelopmentBooks.jar"]