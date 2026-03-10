# Simulador de Frete por CEP

Este projeto foi desenvolvido como evolução do desafio de Padrões de Projeto da DIO.

A proposta original do laboratório era demonstrar a aplicação de alguns Design Patterns com Spring Boot, especialmente `Singleton`, `Strategy` e `Facade`, usando a integração com a API ViaCEP. A partir dessa base, o projeto foi adaptado para um cenário mais simples e objetivo: uma API que recebe um CEP de origem e um CEP de destino, consulta os dois endereços no ViaCEP e calcula um valor de frete com base na localização.

## Objetivo

O objetivo deste projeto é consolidar, na prática, o uso de padrões de projeto em uma aplicação Spring Boot pequena, fácil de entender e com uma regra de negócio real.

Em vez de manter o exemplo original de cadastro de clientes, a aplicação foi evoluída para um simulador de frete por CEP, aproveitando melhor a integração com o ViaCEP e deixando o domínio mais coerente com a proposta final.

## Como o projeto funciona

A API recebe dois CEPs:

- `cepOrigem`
- `cepDestino`

Com esses dados, a aplicação:

1. consulta os dois endereços na API ViaCEP;
2. identifica cidade e estado de origem e destino;
3. escolhe a regra de cálculo adequada;
4. retorna o tipo do frete e o valor calculado.

As regras atuais são simples:

- mesma cidade: `R$ 10,00`
- cidades diferentes no mesmo estado: `R$ 20,00`
- estados diferentes: `R$ 40,00`

## Padrões de Projeto aplicados

### Singleton

Os componentes gerenciados pelo Spring, como classes anotadas com `@Service`, `@Component` e `@RestController`, são instanciados uma única vez e reutilizados pelo container ao longo da execução da aplicação.

### Strategy

O cálculo do frete foi separado em estratégias específicas:

- `FreteMesmaCidadeStrategy`
- `FreteMesmoEstadoStrategy`
- `FreteOutroEstadoStrategy`

Cada classe conhece apenas sua própria regra de cálculo, e o serviço principal seleciona a estratégia adequada com base nos endereços consultados.

### Facade

O endpoint REST exposto pela aplicação funciona como uma fachada para quem consome a API. O cliente não precisa saber como o ViaCEP é chamado nem como a regra de frete é escolhida internamente. Basta enviar os CEPs e receber o resultado do cálculo.

## Tecnologias Utilizadas

- Java 11
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Cloud OpenFeign
- Springdoc OpenAPI / Swagger
- H2 Database
- Maven

## Como Executar

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

## Documentação da API

Depois de iniciar a aplicação, o Swagger estará disponível em:

```text
http://localhost:8080/swagger
```

## Endpoint principal

`POST /fretes/calcular`

Exemplo de requisição:

```json
{
  "cepOrigem": "01001-000",
  "cepDestino": "20040-020"
}
```

Exemplo de resposta:

```json
{
  "cepOrigem": "01001-000",
  "cepDestino": "20040-020",
  "cidadeOrigem": "Sao Paulo",
  "cidadeDestino": "Rio de Janeiro",
  "ufOrigem": "SP",
  "ufDestino": "RJ",
  "tipoFrete": "OUTRO_ESTADO",
  "valorFrete": 40.0
}
```

## Considerações Finais

Este projeto não tem a proposta de reproduzir regras logísticas reais. A ideia é demonstrar, de forma clara e objetiva, a aplicação de padrões de projeto em uma API Spring Boot pequena, funcional e evoluída a partir do laboratório da DIO.
