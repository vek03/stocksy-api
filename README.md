# 🧬 Projeto StockSy - API Rest

StockSY API: Gerenciamento de Matéria-prima e Produtos em Spring Boot com Docker, Redis, OpenSearch e Graylog. 

## 🧩 PARTES DO PROJETO
| Aplicação | Descrição              | Link             |
|-----------|------------------------|------------------|
| API       | API Backend do StockSy | Este Repositório |
| UI        | UI Frontend do StockSy | [UI](https://github.com/vek03/stocksy-ui)           |

> Este projeto não depende de nenhuma outra aplicação para funcionar corretamente

---

# 1.✨ Imagem Docker (DockerHub)

> A imagem desta aplicação é atualizada a cada nova tag ou pull request na [branch main](https://github.com/vek03/stocksy-api)

> Link da imagem no DockerHub: [vek03/stocksy-api:latest](https://hub.docker.com/repository/docker/vek03/stocksy-api)

---

## 1.1 🧩 Containers necessários para rodar a aplicação:

| Container  | Imagem | Link                                                                                                                                           | 
|------------|---|------------------------------------------------------------------------------------------------------------------------------------------------|
| Oracle DB  | `gvenzl/oracle-free:latest` | https://hub.docker.com/r/gvenzl/oracle-free                                                                                                                |
| MongoDB    | `mongo:latest` | https://hub.docker.com/_/mongo                                                                                                                 |
| Redis      | `redis:latest` | https://hub.docker.com/_/redis                                                                                                                 |
| OpenSearch | `opensearchproject/opensearch:2.4.0` | https://hub.docker.com/layers/opensearchproject/opensearch/2.4.0/images/sha256-c8681472b70d46e7de61fe770d288a972f84b3f122f3c74ca06ea525264b6fd5 |
| Graylog    | `graylog/graylog:5.1.5` | https://hub.docker.com/layers/graylog/graylog/5.1.5/images/sha256-3b6967572e88731eacfa661e6d7ca41da3e259bc5eb041e58fb10e4deb823dcb             |

---

## 1.2 ⚙ Variáveis de ambiente necessárias para rodar o container:

| Variável          | Descrição                        | Exemplo                                     |
|-------------------|----------------------------------|---------------------------------------------|
| `SERVER_PORT`     | Porta onde a aplicação irá rodar | `8080`                                      |
| `REDIS_HOST`      | Host do Redis                    | `redis`                                     |
| `REDIS_PORT`      | Porta do Redis                   | `6379`                                      |
| `ORACLE_URL`     | URL de conexão do Oracle         | `jdbc:oracle:thin:@localhost:1521/FREEPDB1` |
| `ORACLE_USERNAME`     | Usuário de conexão do Oracle     | `system`                                    |
| `ORACLE_PASSWORD`     | Senha de conexão do Oracle       | `123456`                                    |
| `GRAYLOG_HOST`    | Endereço do Graylog              | `graylog`                                   |
| `GRAYLOG_PORT`    | Porta do Graylog                 | `12201`                                     |

---

## 1.3 🐳 Como rodar o container

1️⃣ Para baixar a imagem do Docker Hub:
```bash
docker pull vek03/stocksy-api:latest
```

2️⃣ Para rodar o container localmente:
```bash
docker run -d \
    --name stocksy-api \ 
    -e SERVER_PORT=8080 \
    -e REDIS_HOST=redis \
    -e REDIS_PORT=6379 \
    -e ORACLE_URL=jdbc:oracle:thin:@localhost:1521/FREEPDB1 \
    -e ORACLE_USERNAME=system \
    -e ORACLE_PASSWORD=123456 \
    -e GRAYLOG_HOST=graylog \
    -e GRAYLOG_PORT=12201 \
    -p 8080:8080 \
    vek03/stocksy-api:latest
```

3️⃣ Alternativamente, você pode adicionar o serviço no seu docker-compose.yml local, descomentando ou adicionando o seguinte trecho:
```bash
services:
  stocksy-api:
    image: vek03/stocksy-api:latest
    hostname: stocksy-api
    container_name: stocksy-api
    environment:
      SERVER_PORT: 8080
      REDIS_HOST: redis
      REDIS_PORT: 6379
      ORACLE_URL: jdbc:oracle:thin:@oracle-db:1521/FREEPDB1
      ORACLE_USERNAME: system
      ORACLE_PASSWORD: 123456
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
    ports:
      - "8080:8080"
    depends_on:
      oracle-db:
        condition: service_healthy
      mongodb:
        condition: service_healthy
      opensearch:
        condition: service_healthy
      graylog:
        condition: service_started
      redis:
        condition: service_healthy
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:8080/actuator/health || exit 1"]
      interval: 5s
      timeout: 15s
      retries: 10
      start_period: 30s
```

4️⃣ Depois de adicionar o serviço em docker-compose.yml, suba os containers:
```bash
docker-compose up -d
```

---

## 📦 Instalação e Configuração do Ambiente

### 1️⃣ Clone o projeto na sua máquina e baixe as dependências:
```bash
# Clonar
git clone https://github.com/vek03/stocksy-api.git

# Acesse a pasta do projeto
cd stocksy-api
````

### 2️⃣ Suba os containers necessários e Rode o projeto na sua IDE de preferência (ou via comando Maven)
```bash
# Suba os containers necessários (MongoDB, Redis, OpenSearch, Graylog, Oracle)
docker-compose up -d

# Rode o projeto via Maven
```

### 3️⃣ (Opcional) Alternativamente, se quiser rodar via Docker localmente:
```bash
# Dentro da pasta do projeto:
mvn clean package -DskipTests

# Agora faça deploy no Docker local:
docker build -t vek03/stocksy-api:latest .

# Descomente as últimas linhas do docker-compose.yml e rode:
docker-compose up -d
```

> Ou execute o script .bat (executar_tudo.bat) na pasta .commands para automatizar o processo.

> A API Rest StockSy fica disponível na porta 8080 do [Localhost](http://localhost:8080) ao rodar localmente via IDE.

### 4️⃣ (Opcional) Caso deseje, pode rodar o SonarQube localmente

```bash
# Após configurar o pom.xml com as informações do Sonar em Properties:
mvn clean install sonar:sonar -Dsonar.token={TOKEN_SONAR}
```

---

## 🧩 Tecnologias Utilizadas

- **Spring Boot** → Framework Back-End
- **Java** → Linguagem de programação
- **Maven** → Build
- **Docker** → Containers e virtualização
- **Docker Hub** → Repositório de imagens Docker
- **Oracle DB** → Persistência de dados
- **Redis** → Cache
- **OpenSearch e Graylog** → Logs da Aplicação
- **Swagger** → Documentação da API
- **SonarQube** → Qualidade
- **Github Actions** → CI/CD automatizado
- **.bat** → Scripts para automatizar processos no Windows

---

## 📦 Esteira CI/CD Automatizada com Github Actions

> A esteira CI/CD deste projeto é automatizada via Github Actions. A cada tag criada a esteira é disparada.

### Para executar a Esteira pelo trigger:
```bash
# Exemplo: Cria a tag
git tag <version>

# Envia a tag para o repositório remoto
git push origin <version>
```

[![StockSy API CI/CD Workflow](https://github.com/vek03/stocksy-api/actions/workflows/main.yml/badge.svg)](https://github.com/vek03/stocksy-api/actions/workflows/main.yml)

---

## Postman Collection

> Link para download da coleção Postman utilizada nos testes da API: [Postman Collection StockSy API](https://www.postman.com/aviation-pilot-88658184/workspace/my-workspace/collection/33703402-e6346ef1-a297-4172-bfe6-554313b2341e?action=share&creator=33703402&active-environment=33703402-ffbccf32-8c55-4697-856c-20ff1374f0a3)

> Alternativamente, você pode utilizar o Swagger UI para testar a API enquanto ela estiver rodando localmente, acessando o link:
[Swagger UI StockSy API](http://localhost:8080/swagger-ui/index.html) (rodando localmente)

---
