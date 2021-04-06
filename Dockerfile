FROM openjdk:11.0.10-jdk-slim

RUN mkdir app
RUN ls

COPY target/ app/

ENTRYPOINT ["java", "-jar", "/app.jar"] 
