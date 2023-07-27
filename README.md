# vote bem
![C4 Model - L1 Containner](/src/main/resources/doc/containners-c4.png)

## Getting started
### Requirements
* Java 11+
* Maven 3+
* Docker
* Git
* AWS CLI

### Download Projeto
* Clone o projeto na máquina
* Acesse o diretório do projeto clonado via terminal

### Configure AWS CLI profile localstack
```configure
aws configure --profile localstack
AWS Access Key ID [None]: teste
AWS Secret Access Key [None]: teste
Default region name [None]: us-east-1
Default output format [None]: json
```

### Invoking Spring Boot server Windows
```windows
docker-compose up
sh/create-sqs-and-sns--resultado-sessao.bat
mvn spring-boot:run
```
### Invoking Spring Boot server Linux
```linux
docker-compose up
sh sh/create-sqs-and-sns--resultado-sessao.sh
mvn spring-boot:run
```

### Link to access Swagger-UI local
- [Swagger Local](http://localhost:8080/vote-bem/swagger-ui)

## Test using Postman
### Requirements
* [Postman](https://www.postman.com/downloads/)

### OR - Link to Test API Collection local
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/21826072-26c31fa2-8887-4f5a-9853-6dbbfd32c004?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D21826072-26c31fa2-8887-4f5a-9853-6dbbfd32c004%26entityType%3Dcollection%26workspaceId%3Db3ef8f7a-4700-4724-904c-9ef6e16d7a58)