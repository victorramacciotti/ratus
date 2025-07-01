
# 🛠️ Projeto Spring Boot Configurado

Este projeto foi iniciado com o framework Spring Boot, já com as principais configurações realizadas para facilitar o desenvolvimento. As tecnologias integradas até o momento incluem:

- Spring Boot – Framework para aplicações Java
- MySQL – Banco de dados relacional
- Swagger – Documentação interativa da API REST que pode ser acessada através do link http://localhost:8080/swagger-ui.html

## ⚙️ Como configurar o application.properties
As configurações da aplicação estão centralizadas no arquivo application.properties, localizado na pasta:
`src/main/resources/application.properties` se o arquivo `application.properties` não existir, crie-o.

Abaixo está um exemplo de como esse arquivo deve ser configurado:

### Configurações da Aplicação Spring Boot

server.port=8080

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=SEU_USUARIO_AQUI
spring.datasource.password=SUA_SENHA_AQUI
spring.datasource.url=jdbc:mysql://SEU_HOST_AQUI:PORTA/SEU_BANCO_AQUI

spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

copie e cole o código acima no `application.properties` e substitua os dados pelos seus.