
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

spring.flyway.enabled=true

[comment] O mais seguro é configurar uma variável de ambiente para isso e tirar depois dos ':'
api.security.token.secret="${JWT_SECRET:my-secret-key}"

copie e cole o código acima no `application.properties` e substitua os dados pelos seus.

## ⚙️ Configurações do Banco de Dados

É necessário adicionar um usuário no banco de dados para fazer login, visto que a rota "/register" está liberada apenas para usuários com role admin. Para cadastrar novo usuário, basta você logar com um usuário admin e utilizar a rota "/register" com as informações do novo usuário.

Para inserir o usuario com role admin, segue o INSERT recomendado:

INSERT INTO `user` (`login`, `password`, `role`) VALUES
('ADMIN', '$2a$10$ZDH/rZJWz73RSOQ6FzwfrOTz7lPkzfBTKej1.EB5w2qqtocwSOzFa', 0);

Para Logar nesse usuário

login: "ADMIN", </br>
password: "ratus.academia.admin"