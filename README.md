# 🏦 ATM API RESTful (Caixa Eletrônico)

<p align="center">
  <img src="https://img.shields.io/badge/Java-21%2F25-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/PostgreSQL-Cloud-blue?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker">
  <img src="https://img.shields.io/badge/Status-Online-success?style=for-the-badge" alt="Status">
</p>

<p align="center">
  <strong><a href="https://tela-atm-api.vercel.app/">🔗 Acessar Aplicação Web</a></strong> | 
  <strong><a href="https://atm-api-37wn.onrender.com/swagger-ui/index.html">📚 Documentação Swagger (API)</a></strong>
</p>

API RESTful desenvolvida em **Java** com **Spring Boot** para simular as operações de um caixa eletrônico (ATM). O projeto conta com persistência de dados em nuvem, conteinerização via Docker e foca em boas práticas de engenharia de software e segurança.

---

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java (JDK 21/25)
* **Framework:** Spring Boot (Spring Data JPA, Spring Web, Validation)
* **Banco de Dados:** PostgreSQL (hospedado via Neon)
* **Build:** Maven
* **Infraestrutura:** Docker & Deploy (Render/Vercel)
* **Documentação:** Springdoc OpenAPI (Swagger)

---

## ⚙️ Funcionalidades

* 🟢 **Abertura de Conta:** Cadastro seguro com criptografia de senha.
* 🔍 **Consulta de Saldo:** Busca de informações da conta pelo número.
* 💵 **Depósito:** Atualização atômica do saldo da conta.
* 💸 **Saque:** Validação de saldo e autenticação da operação.
* 📄 **Extrato:** Histórico detalhado (tipo de operação, valor e data/hora).

---

## 📍 Endpoints da API

Abaixo estão as rotas principais geradas pela documentação interativa (Swagger).

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/accounts` | Cria uma nova conta bancária. |
| `GET` | `/accounts/{numberAccount}` | Retorna os detalhes e saldo de uma conta específica. |
| `POST` | `/accounts/{numberAccount}/deposit` | Realiza um depósito na conta informada. |
| `POST` | `/accounts/{numberAccount}/withdraw` | Realiza um saque na conta informada (valida saldo). |

### Exemplo de Requisição: Saque (`POST /accounts/{numberAccount}/withdraw`)

**Request:**
```json
{
  "amount": 150.00,
  "password": "senha_segura"
}