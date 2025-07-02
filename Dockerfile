# --- Estágio 1: Build da Aplicação ---
# Usamos uma imagem do Maven com Java 17 para compilar o projeto
FROM maven:3.9.6-eclipse-temurin-17-focal AS build

# Define o diretório de trabalho dentro do container de build
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar o cache do Docker
COPY pom.xml .

# Copia todo o código-fonte
COPY src ./src

# Executa o build do Maven para gerar o arquivo .jar
# -DskipTests pula a execução de testes, tornando o build mais rápido
RUN mvn clean package -DskipTests


# --- Estágio 2: Imagem Final de Execução ---
# Usamos uma imagem leve, contendo apenas o Java 17 necessário para rodar
FROM openjdk:17-slim

# Define o diretório de trabalho na imagem final
WORKDIR /app

# Copia apenas o .jar gerado no estágio 'build' para a nossa imagem final
# Isso torna a imagem final muito menor e mais segura
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta que a aplicação Spring Boot usa internamente
EXPOSE 8080

# Comando para iniciar a aplicação quando o container for executado
ENTRYPOINT ["java", "-jar", "app.jar"]