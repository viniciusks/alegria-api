@echo off
SET VERSION=%1

IF "%VERSION%"=="" (
    echo Sem versao
    exit 0
) ELSE (
    echo ..:: Iniciando a compilacao da imagem spring-boot Docker ::..
    cd .. && mvn spring-boot:build-image

    echo ..:: Criando tag da versao-parametro ::..
    docker tag alegria-api:0.0.1-SNAPSHOT viniciusks13/alegria-api:%VERSION%
    docker tag alegria-api:0.0.1-SNAPSHOT viniciusks13/alegria-api:latest
    echo viniciusks13/alegria-api:%VERSION%
    echo viniciusks13/alegria-api:latest

    echo ..:: Subindo imagem Docker no Docker Hub ::..
    docker push viniciusks13/alegria-api:%VERSION%
    docker push viniciusks13/alegria-api:latest

    echo ..:: Deploy no Kubernetes ::..
    @REM cd C:\www\concafras\iac-k8s\alegria-api
    kubectl delete -f C:\www\concafras\iac-k8s\alegria-api\alegria.yml
    kubectl apply -f C:\www\concafras\iac-k8s\alegria-api\alegria.yml
    @REM kubectl set image deployment/alegria-api  ac-api-container=viniciusks13/alegria-api:%VERSION% -n alegria-digital
)