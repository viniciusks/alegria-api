FROM openjdk:11-jdk
EXPOSE 8081
ARG JAR_FILE=target/alegria-api-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app.jar"]