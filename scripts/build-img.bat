@echo off
SET VERSION=%1
SET /A FLAG_DEPLOY=0

IF "%VERSION%"=="" (
    echo "Sem versao"
    exit 0
) ELSE (
    @REM echo "..:: Iniciando a compilacao da imagem spring-boot Docker ::.."
    @REM cd .. && mvn spring-boot:build-image

    @REM echo "..:: Criando tag da versao-parametro ::.."
    @REM docker tag alegria-api:0.0.1-SNAPSHOT viniciusks13/alegria-api:%VERSION%

    @REM echo "..:: Subindo imagem Docker no Docker Hub ::.."
    @REM docker pull viniciusks13/alegria-api:%VERSION%
    SET /A FLAG_DEPLOY=1

    IF %FLAG_DEPLOY%==0 (
        echo "Nao faz deploy"
    ) ELSE (
        echo "Faz deploy"
    )
)