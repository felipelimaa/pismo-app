# Pismo APP
Este projeto foi desenvolvido para gerenciar o controle de transações de uma conta, contendo os seguintes recursos:
* Criação de uma conta (Accounts)
* Realização de transações (Transactions)
* Baseado em tipos de operações (Operational Types) lançar transações com valor (amount) positivo (pagamento) ou negativo (compras ou saque).

### Pré-requisitos
Para executar o projeto, é necessário que se tenha instalado em seu PC os seguintes recursos:
* OpenJDK 11 (https://adoptopenjdk.net/)
* Docker (https://docs.docker.com/engine/install/)
* Docker Compose (https://docs.docker.com/compose/install/)

### Ambiente de desenvolvimento
O projeto é executado através do _Spring Boot_ como framework backend, utilizando a linguagem _Groovy_ e banco de dados _PostgreSQL_ para armazenamento dos dados.

Para executar o _PostgreSQL_ como container, utilize o seguinte comando:
```
docker-compose up -d postgres-server
```

Assim, o servidor do banco de dados rodará localmente escutando a porta **5432**, com os seguintes dados de autenticação:

- **usuário:** pismo
- **senha**: pismo
- **conexão**: localhost:5432

Após o servidor do banco de dados estar em execução, abra o projeto na sua IDE desejada ou, caso deseje executar o ambiente baseado no código, execute o seguinte comando:

```
./gradlew bootRun
```

Através de uma IDE, é possível executar a classe: `AppApplication.groovy`

## Testes
Os testes foram desenvolvidos utilizando o **MockMVC** e banco de dados **H2** para guardar os dados em memória.

O arquivo profile utilizado para definir as configuraçõs dos testes é o ```application-test.yml```.

Caso queira executar os testes, utilize o seguinte comando:
```
./gradlew test
```

## Execução do projeto completo (build and deploy)
O projeto contempla um arquivo `docker-compose.yaml` e um `Dockerfile` composto com o processo de build e execução do projeto.

Caso deseje executar o projeto completo, com a build e ambiente de execução, bem como o servidor **PostgreSQL**, na pasta do projeto, execute o seguinte comando:

```
docker-compose up -d 
```