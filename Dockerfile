# Usa uma imagem base com Java 18
FROM openjdk:18-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado pelo build para o container
COPY build/libs/por_service-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 (a mesma usada pela aplicação)
EXPOSE 8080

# Comando para executar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]