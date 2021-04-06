FROM openjdk:11.0.10-jdk-slim
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/spring-docker-spotify.jar
WORKDIR /app
ENTRYPOINT java -jar spring-docker-spotify.jar