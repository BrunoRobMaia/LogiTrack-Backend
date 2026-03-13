# 🚛 LogiTrack Pro

<div align="center">

![LogiTrack Pro Logo](https://img.shields.io/badge/LogiTrack-Pro-blue?style=for-the-badge&logo=truck&logoColor=white)

**Sistema de Gestão de Frotas — LogAp IT Solutions**

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-green?style=flat-square&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=flat-square&logo=docker)](https://www.docker.com/)

</div>

---

## 📋 Sumário

- [Sobre o Projeto](#-sobre-o-projeto)
- [🚀 Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologias](#️-tecnologias)
- [📋 Pré-requisitos](#-pré-requisitos)
- [🚀 Instalação e Execução](#-instalação-e-execução)
- [🐳 Docker (Recomendado)](#-docker-recomendado)
- [📊 Estrutura da API](#-estrutura-da-api)
- [🗄️ Banco de Dados](#️-banco-de-dados)
- [🔐 Autenticação](#-autenticação)
- [📝 Documentação da API](#-documentação-da-api)
- [🧪 Testes](#-testes)
- [🤝 Contribuição](#-contribuição)
- [📄 Licença](#-licença)

---

## 📖 Sobre o Projeto

O **LogiTrack Pro** é um sistema completo de gestão de frotas desenvolvido pela LogAp IT Solutions, projetado para otimizar o controle de veículos, viagens e manutenções. Com uma interface RESTful robusta e segura, o sistema oferece total visibilidade e controle sobre as operações logísticas.

### 🎯 Objetivos Principais

- **Gestão Eficiente**: Controle centralizado de toda a frota
- **Rastreamento em Tempo Real**: Monitoramento de viagens e veículos
- **Manutenção Preventiva**: Agendamento e controle de serviços
- **Relatórios Inteligentes**: Análise de dados e projeções
- **Segurança**: Autenticação JWT e controle de acesso

---

## 🚀 Funcionalidades

### 🚗 Gestão de Veículos

- Cadastro de veículos leves e pesados
- Consulta por placa, modelo e tipo
- Histórico completo do veículo

### 🛣️ Controle de Viagens

- Registro de viagens com origem e destino
- Controle de quilometragem percorrida
- Histórico de viagens por veículo

### 🔧 Manutenção Programada

- Agendamento de serviços de manutenção
- Controle de custos e status
- Histórico de manutenções por veículo

### 📊 Dashboard e Relatórios

- Visão geral da frota
- Projeções de custos de manutenção
- Análise de utilização dos veículos

### 🔐 Segurança

- Autenticação via JWT
- Controle de acesso por usuário
- API protegida com Spring Security

---

## 🛠️ Tecnologias

| Tecnologia          | Versão | Descrição                     |
| ------------------- | ------ | ----------------------------- |
| **Java**            | 21     | Linguagem principal           |
| **Spring Boot**     | 3.4.5  | Framework principal           |
| **Spring Security** | 3.4.5  | Segurança e autenticação      |
| **Spring Data JPA** | 3.4.5  | Persistência de dados         |
| **PostgreSQL**      | 15     | Banco de dados relacional     |
| **JWT**             | 0.11.5 | Tokens de autenticação        |
| **Lombok**          | -      | Redução de código boilerplate |
| **Docker**          | -      | Containerização               |
| **Maven**           | -      | Gerenciamento de dependências |

---

## 📋 Pré-requisitos

### Ambiente de Desenvolvimento

- **Java 21** ou superior
- **Maven 3.8+**
- **PostgreSQL 15+** (se executar localmente)
- **IDE** (IntelliJ IDEA, Eclipse ou VS Code)

### Para Docker

- **Docker Desktop** instalado
- **Docker Compose** (geralmente incluído no Docker Desktop)

---

## 🚀 Instalação e Execução

### ⚠️ Passo 0: Configurar Variáveis de Ambiente

**Antes de iniciar**, configure as variáveis de ambiente obrigatórias:

```bash
# 1. Copiar o arquivo de exemplo
cp application.properties.example application.properties

# 2. Editar o arquivo application.properties com suas credenciais
```

````bash

### Opção 1: Execução Local

1. **Clone o repositório**

   ```bash
   git clone https://github.com/seu-usuario/logitrack.git
   cd logitrack
   ```

2. **Configure o banco de dados PostgreSQL**

   ```sql
   CREATE DATABASE logitrack;
   CREATE USER postgres WITH PASSWORD 'sua_senha_aqui';
   GRANT ALL PRIVILEGES ON DATABASE logitrack TO postgres;
   ```

   > 🔒 **Segurança**: Configure credenciais seguras e utilize variáveis de ambiente em produção.

3. **Execute as migrações**

   ```bash
   psql -U postgres -d logitrack -f sql/init.sql
   ```

4. **Inicie a aplicação**

   ```bash
   ./mvnw spring-boot:run
   ```

5. **Acesse a API**
   - URL: http://localhost:8080
   - Health Check: http://localhost:8080/actuator/health

---

## 🐳 Docker (Recomendado)

#### 🚀 Início Rápido

1. **Clone e configure variáveis de ambiente**

   ```bash
   git clone https://github.com/seu-usuario/logitrack.git
   cd logitrack

   # Configurar variáveis de ambiente
   cp application-docker.properties.example application-docker.properties
   # Editar application-docker.properties com suas credenciais
   # Nota: O docker-compose.yml já está configurado para usar este arquivo, só adicione suas credenciais
   # environment:
   #   POSTGRES_DB: logitrack
   #   POSTGRES_USER: seu_usuario
   #   POSTGRES_PASSWORD: sua_senha

   ```

2. **Inicie com Docker**

   ```bash
   docker-compose up --build
   ```

3. **Aguarde os serviços iniciarem**
   - A aplicação estará disponível em: http://localhost:8080
   - Banco de dados: localhost:5432

4. **Verifique os serviços**
   ```bash
   docker-compose ps
   ```

## 📊 Estrutura da API

### Endpoints da API

#### 🔐 Autenticação

| Endpoint             | Método | Descrição              | Autenticação |
| -------------------- | ------ | ---------------------- | ------------ |
| `/api/auth/register` | POST   | Registrar novo usuário | Não          |
| `/api/auth/login`    | POST   | Autenticar usuário     | Não          |

#### 🚗 Veículos

| Endpoint             | Método | Descrição                | Autenticação |
| -------------------- | ------ | ------------------------ | ------------ |
| `/api/veiculos`      | GET    | Listar todos os veículos | Sim          |
| `/api/veiculos/{id}` | GET    | Buscar veículo por ID    | Sim          |
| `/api/veiculos`      | POST   | Criar novo veículo       | Sim          |
| `/api/veiculos/{id}` | PUT    | Atualizar veículo        | Sim          |
| `/api/veiculos/{id}` | DELETE | Excluir veículo          | Sim          |

#### 🛣️ Viagens

| Endpoint            | Método | Descrição               | Autenticação |
| ------------------- | ------ | ----------------------- | ------------ |
| `/api/viagens`      | GET    | Listar todas as viagens | Sim          |
| `/api/viagens/{id}` | GET    | Buscar viagem por ID    | Sim          |
| `/api/viagens`      | POST   | Criar nova viagem       | Sim          |
| `/api/viagens/{id}` | PUT    | Atualizar viagem        | Sim          |
| `/api/viagens/{id}` | DELETE | Excluir viagem          | Sim          |

#### 🔧 Manutenções

| Endpoint                    | Método | Descrição                   | Autenticação |
| --------------------------- | ------ | --------------------------- | ------------ |
| `/api/manutencoes`          | GET    | Listar todas as manutenções | Sim          |
| `/api/manutencoes/{id}`     | GET    | Buscar manutenção por ID    | Sim          |
| `/api/manutencoes`          | POST   | Criar nova manutenção       | Sim          |
| `/api/manutencoes/{id}`     | PUT    | Atualizar manutenção        | Sim          |
| `/api/manutencoes/{id}`     | DELETE | Excluir manutenção          | Sim          |
| `/api/manutencoes/proximas` | GET    | Listar próximas manutenções | Sim          |

#### 📊 Dashboard e Relatórios

| Endpoint                   | Método | Descrição                  | Autenticação |
| -------------------------- | ------ | -------------------------- | ------------ |
| `/api/dashboard`           | GET    | Dashboard geral do sistema | Sim          |
| `/api/projecao/financeira` | GET    | Projeção financeira mensal | Sim          |

### Exemplo de Requisição

```bash
# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"usuario@exemplo.com","password":"senha123"}'

# Registrar usuário
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"novo@exemplo.com","password":"senha123","nome":"Novo Usuário"}'

# Listar veículos (com autenticação)
curl -X GET http://localhost:8080/api/veiculos \
  -H "Authorization: Bearer SEU_TOKEN_JWT"
```

> ⚠️ **Importante:** Ao rodar o projeto pela primeira vez (local ou via Docker),
> a tabela `usuarios` é criada automaticamente pelo Spring Boot (via JPA/Hibernate).
> Para usar o sistema, é necessário fazer um registro via frontend (web)
> com um e-mail e senha.

### Dados Iniciais

O sistema inclui dados de exemplo para demonstração:

- 4 veículos cadastrados
- 3 viagens registradas
- 3 manutenções agendadas

---

## 🔐 Autenticação

### JWT Token Flow

1. **Registro**: Crie uma conta via `/api/auth/register`
2. **Login**: Envie credenciais para `/api/auth/login`
3. **Token**: Receba um JWT válido (configurável via variáveis de ambiente)
4. **Acesso**: Inclua o token no header `Authorization: Bearer <token>`

> 🔒 **Segurança**: As configurações de JWT (secret, expiration) são gerenciadas via variáveis de ambiente e não expostas no código.

---

## 📄 Licença

Este projeto está licenciado sob a **MIT License** - veja o arquivo [LICENSE](LICENSE) para detalhes.

---
````

<div align="center">

**Desenvolvido por Bruno Roberto**

[![GitHub](https://img.shields.io/badge/Github-perfil-black?style=for-the-badge&logo=github)](https://github.com/BrunoRobMaia)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-perfil-blue?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/brunorobertomaia/)

</div>
