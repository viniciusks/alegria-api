@echo off
SET VERSION=%1

IF "%VERSION%"=="" (
    echo "Sem versao"
    exit 0
) ELSE (
    echo "..:: Iniciando a compilacao da imagem spring-boot Docker ::.."
    cd .. && mvn spring-boot:build-image

    echo "..:: Criando tag da versao-parametro ::.."
    docker tag alegria-api:0.0.1-SNAPSHOT viniciusks13/alegria-api:%VERSION%

    echo "..:: Subindo imagem Docker no Docker Hub ::.."
    docker push viniciusks13/alegria-api:%VERSION%
)