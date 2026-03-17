# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copia dependências primeiro (cache layer)
COPY .mvn .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copia o código e builda
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime (imagem final bem menor)
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/logitrack-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT ["java", "-jar", "app.jar"]
