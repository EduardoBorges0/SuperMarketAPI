# 🛒 Supermarket API - Java Spring Boot

Este projeto é uma API REST desenvolvida em **Java com Spring Boot**, que simula um sistema de gerenciamento de produtos para supermercados.

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- JUnit e Mockito (Testes)
- Maven
- Git

---

## ✅ Pré-requisitos

Antes de rodar o projeto, você precisará ter instalado na sua máquina:

- [Java JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [PostgreSQL](https://www.postgresql.org/)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)

---

## ⚙️ Configuração do banco de dados

1. Crie um banco no PostgreSQL com o nome desejado, ex:

CREATE DATABASE supermarket_api;

2. Altere o arquivo application.properties em:
    - src/main/resources/application.properties

Exemplo de configuração:

spring.datasource.url=jdbc:postgresql://localhost:5432/supermarket_api
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

1. Clone o repositório
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio

2. Compile e rode a aplicação com Maven
mvn spring-boot:run

Ou, para compilar e gerar o .jar:
mvn clean install
java -jar target/seu-arquivo.jar

📫 Endpoints principais
Após iniciar, a API estará disponível em:

http://localhost:8080

Endpoints:

GET /market/getEveryMarket
GET /market/getEveryMarket
POST /market/createMarket
DELETE /market/deleteMarket/{id}
GET /product/getEveryProduct - Listar produtos
GET /product/getProductById/{id} - Buscar produto por ID
POST /product/createProduct - Criar novo produto
DELETE product/deleteProduct/{id} - Remover produto

🧪 Testes
Para rodar os testes:
mvn test

📌 Observações
Use ferramentas como Postman ou Insomnia para testar os endpoints.
A API segue os princípios RESTful.

👨‍💻 Autor
Desenvolvido por Eduardo Borges
🔗 LinkedIn: https://www.linkedin.com/in/eduardoo-borges/
