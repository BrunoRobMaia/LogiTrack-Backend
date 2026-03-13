# Docker Logitrack

Este documento contém as instruções para executar o sistema Logitrack utilizando Docker.

## Pré-requisitos

- Docker Desktop instalado
- Docker Compose (geralmente incluído no Docker Desktop)

## Como executar

### 1. Construir e iniciar os containers

No diretório raiz do projeto, execute:

```bash
docker-compose up --build
```

Este comando irá:
- Construir a imagem Docker da aplicação
- Iniciar o container PostgreSQL
- Iniciar a aplicação Spring Boot
- Aguardar o banco de dados ficar pronto antes de iniciar a aplicação

### 2. Acessar a aplicação

- API REST: http://localhost:8080
- Banco de dados: localhost:5432

### 3. Parar os containers

```bash
docker-compose down
```

Para remover também os volumes (dados do banco):
```bash
docker-compose down -v
```

## Comandos úteis

### Ver logs dos containers
```bash
# Ver todos os logs
docker-compose logs

# Ver logs apenas da aplicação
docker-compose logs app

# Ver logs apenas do banco
docker-compose logs db
```

### Executar comandos nos containers
```bash
# Acessar o container da aplicação
docker-compose exec app bash

# Acessar o container do banco
docker-compose exec db psql -U postgres -d logitrack
```

### Reconstruir apenas a aplicação
```bash
docker-compose up --build app
```

## Estrutura dos containers

- **app**: Aplicação Spring Boot (porta 8080)
- **db**: Banco PostgreSQL (porta 5432)
- **postgres_data**: Volume persistente para os dados do banco

## Configurações

As configurações específicas para Docker estão no arquivo `application-docker.properties`.

Variáveis de ambiente configuradas no docker-compose:
- `SPRING_DATASOURCE_URL`: URL do banco PostgreSQL
- `SPRING_DATASOURCE_USERNAME`: Usuário do banco
- `SPRING_DATASOURCE_PASSWORD`: Senha do banco
- `SPRING_JPA_HIBERNATE_DDL_AUTO`: Estratégia de criação de tabelas
- `SPRING_WEB_CORS_ALLOWED_ORIGINS`: Origens permitidas para CORS

## Solução de problemas

### Aplicação não inicia
1. Verifique se as portas 8080 e 5432 estão disponíveis
2. Execute `docker-compose logs app` para ver os logs de erro
3. Limpe os containers e volumes: `docker-compose down -v` e tente novamente

### Banco de dados não conecta
1. Aguarde mais tempo para o PostgreSQL inicializar completamente
2. Verifique os logs do banco: `docker-compose logs db`
3. O healthcheck garante que a aplicação só inicie após o banco estar pronto

### Portas em uso
Se as portas estiverem em uso, você pode alterá-las no arquivo `docker-compose.yml`:
```yaml
ports:
  - "8081:8080"  # Muda a porta externa para 8081
```
