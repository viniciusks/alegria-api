FROM openjdk:11.0.10-jdk-slim
RUN mkdir /app
COPY . /app
RUN mvn clean install
ADD /target/*.jar /app/app_alegria_api.jar
WORKDIR /app
ENTRYPOINT java -jar app_alegria_api.jar