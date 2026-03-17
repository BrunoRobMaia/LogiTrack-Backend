# 🚛 LogiTrack Pro

**Sistema de Gestão de Frotas — LogAp IT Solutions**

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-green?style=flat-square&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=flat-square&logo=docker)](https://www.docker.com/)

---

## Sobre o Projeto

O **LogiTrack Pro** é um sistema completo de gestão de frotas desenvolvido pela LogAp IT Solutions, projetado para otimizar o controle de veículos, viagens e manutenções. Com uma interface RESTful robusta e segura, o sistema oferece total visibilidade e controle sobre as operações logísticas.

### 🎯 Funcionalidades Principais

- **Gestão de Veículos**: Cadastro e consulta de veículos leves e pesados
- **Controle de Viagens**: Registro de viagens com quilometragem e histórico
- **Manutenção Programada**: Agendamento de serviços e controle de custos
- **Dashboard**: Relatórios e projeções financeiras
- **Segurança**: Autenticação JWT e controle de acesso

---

## � Instalação Rápida

### Pré-requisitos

- **Java 21** ou superior
- **Maven 3.8+**
- **Docker Desktop** (recomendado)

### Opção 1: Docker (Recomendado)

```bash
# 1. Clonar repositório
git clone https://github.com/BrunoRobMaia/LogiTrack-Backend.git

# 2. Configurar propriedades Docker (rode o comando no terminal do VS Code)
cp src/main/resources/application-docker.properties.example src/main/resources/application-docker.properties
# Edite com suas credenciais

# 3. Configurar docker-compose.yml
# Edite as credenciais do banco de dados no arquivo docker-compose.yml

# 4. Iniciar serviços
docker-compose up --build

# 5. Acessar aplicação
# URL: http://localhost:8080
```

### Opção 2: Execução Local

```bash
# 1. Configurar banco PostgreSQL
CREATE DATABASE logitrack;

# 2. Configurar propriedades (rode o comando no terminal do VS Code)
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Edite com suas credenciais

# 3. Copie o conteúdo do arquivo sql/init.sql e execute no seu banco de dados

# 4. Iniciar aplicação
./mvnw spring-boot:run

# 5. Acessar: http://localhost:8080
```

---

## 📊 API Endpoints

### 🔐 Autenticação

| Endpoint             | Método | Descrição         |
| -------------------- | ------ | ----------------- |
| `/api/auth/register` | POST   | Registrar usuário |
| `/api/auth/login`    | POST   | Login             |

### 🚗 Veículos

| Endpoint             | Método | Descrição         |
| -------------------- | ------ | ----------------- |
| `/api/veiculos`      | GET    | Listar veículos   |
| `/api/veiculos/{id}` | GET    | Buscar veículo    |
| `/api/veiculos`      | POST   | Criar veículo     |
| `/api/veiculos/{id}` | PUT    | Atualizar veículo |
| `/api/veiculos/{id}` | DELETE | Excluir veículo   |

### 🛣️ Viagens

| Endpoint            | Método | Descrição        |
| ------------------- | ------ | ---------------- |
| `/api/viagens`      | GET    | Listar viagens   |
| `/api/viagens/{id}` | GET    | Buscar viagem    |
| `/api/viagens`      | POST   | Criar viagem     |
| `/api/viagens/{id}` | PUT    | Atualizar viagem |
| `/api/viagens/{id}` | DELETE | Excluir viagem   |

### 🔧 Manutenções

| Endpoint                    | Método | Descrição            |
| --------------------------- | ------ | -------------------- |
| `/api/manutencoes`          | GET    | Listar manutenções   |
| `/api/manutencoes/{id}`     | GET    | Buscar manutenção    |
| `/api/manutencoes`          | POST   | Criar manutenção     |
| `/api/manutencoes/{id}`     | PUT    | Atualizar manutenção |
| `/api/manutencoes/{id}`     | DELETE | Excluir manutenção   |
| `/api/manutencoes/proximas` | GET    | Próximas manutenções |

### 📊 Dashboard

| Endpoint                   | Método | Descrição           |
| -------------------------- | ------ | ------------------- |
| `/api/dashboard`           | GET    | Dashboard geral     |
| `/api/projecao/financeira` | GET    | Projeção financeira |

### Exemplo de Uso

```bash
# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"usuario@exemplo.com","password":"senha123"}'

# Listar veículos (com token)
curl -X GET http://localhost:8080/api/veiculos \
  -H "Authorization: Bearer SEU_TOKEN_JWT"
```

---

## 🛠️ Tecnologias

| Tecnologia      | Versão | Uso                 |
| --------------- | ------ | ------------------- |
| Java            | 21     | Linguagem principal |
| Spring Boot     | 3.4.5  | Framework           |
| Spring Security | 3.4.5  | Segurança           |
| PostgreSQL      | 15     | Banco de dados      |
| JWT             | 0.11.5 | Autenticação        |
| Docker          | -      | Containerização     |
| Maven           | -      | Build/Dependências  |

---

## 🔐 Autenticação

### Fluxo JWT

1. **Registro**: POST `/api/auth/register`
2. **Login**: POST `/api/auth/login` → Recebe JWT
3. **Acesso**: Header `Authorization: Bearer <token>`

### Response de Login

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "expiresIn": 86400
}
```

---

## 📄 Licença

Este projeto está licenciado sob a **MIT License**.

<div align="center">

**Desenvolvido por Bruno Roberto**

[![GitHub](https://img.shields.io/badge/Github-perfil-black?style=for-the-badge&logo=github)](https://github.com/BrunoRobMaia)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-perfil-blue?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/brunorobertomaia/)

</div>
