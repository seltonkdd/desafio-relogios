# Gerenciamento de estoque de relógios

Aplicação feita com Java e Spring Boot, simulando um gerenciamento de estoque de relógios. Esse desafio faz parte do módulo 4 do curso Java10x.

## Tecnologias Utilizadas
- **Java 17+**
- **Spring Boot**
- **Maven**
- **PostgreSQL 17+**
- **Jakarta Validator**
- **Lombok**

## Estrutura do projeto

```
src/
├── main/
│   ├── java/selton/dev/desafio_relogios/
|   |   ├── config/
│   │   │   └── CarregadorDadosInicial.java
│   │   ├── controller/
│   │   │   └── RelogioController.java
│   │   ├── dto/
│   │   │   ├── RelogioDTO.java
│   │   │   ├── request/
|   |   |   |   ├── AtualizadorRelogioRequest.java
|   |   |   |   └── CriarRelogioRequest.java
│   │   │   └── response/
|   |   |       └── ResponseDTO.java
│   │   ├── exception/
│   │   │   ├── ErrorResponse.java
|   |   |   ├── GlobalExceptionHandler.java
│   │   │   └── custom/
│   │   |       └── RelogioNaoEncontradoException.java
|   |   ├── mapper/
|   |   |   └── RelogioMapper.java
│   │   ├── model/
|   |   |   ├── Relogio.java
|   |   |   └── enums/
|   |   |       ├── EtiquetaResistenciaAgua.java
|   |   |       ├── MaterialCaixa.java
|   |   |       ├── TipoMovimento.java
|   |   |       └── TipoVidro.java
|   |   ├── repository/
|   |   |   └── RelogioRepository.java
|   |   ├── service/
|   |   |   ├── RelogioService.java
|   |   |   ├── RelogioSpecs.java
|   |   |   └── enums/
|   |   |       └── OrdenacaoRelogios.java
│   │   └── DesafioRelogiosApplication.java
│   └── resources/
└── test
```

## Como Executar
### Pré-requisitos:
>>> PostgreSQL 17
Configure seu `aplication.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/relogios_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

Para rodar:
   ```sh
   ./mvnw spring-boot:run
   ```
Acesse a aplicação em `http://localhost:8080`.