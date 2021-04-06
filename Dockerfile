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
RUN cd /app && ls && mvn clean install

# Adicionando .jar para o workdir
ADD /target/*.jar /app/app_alegria_api.jar
WORKDIR /app
ENTRYPOINT java -jar app_alegria_api.jar