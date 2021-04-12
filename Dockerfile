FROM ubuntu:20.04

# Preparando o workspace
RUN mkdir /app
COPY . /app

# Instalando dependências
RUN apt update -y
RUN apt install -y maven default-jdk

# Verificando versão do Java
RUN java --version

# Executando build
RUN cd /app && mvn clean install

# Adicionando .jar para o workdir
# RUN cp /app/target/alegria-api-0.0.1-SNAPSHOT.jar /app && mv alegria-api-0.0.1-SNAPSHOT.jar app_alegria_api.jar

WORKDIR /app

ENTRYPOINT java -jar /app/target/alegria-api-0.0.1-SNAPSHOT.jar