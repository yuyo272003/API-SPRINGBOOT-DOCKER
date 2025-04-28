# Stage 1: build
FROM maven:3.8.7-openjdk-17 AS builder
WORKDIR /app

# Copiamos sólo lo necesario para caché de dependencias
COPY pom.xml .
COPY src ./src

# Compilamos y empaquetamos el JAR (sin tests para agilizar)
RUN mvn clean package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=/app/target/*.jar
COPY --from=builder ${JAR_FILE} app.jar

# Exponemos el puerto de tu API
EXPOSE 8888

# Arranque por defecto
ENTRYPOINT ["java","-jar","/app.jar"]
