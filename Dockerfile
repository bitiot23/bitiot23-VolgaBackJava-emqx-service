# Etapa 1: Compilación del proyecto
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos los archivos necesarios para construir el proyecto
COPY pom.xml .
COPY src ./src

# Construimos el proyecto y empaquetamos el JAR
RUN mvn clean package -DskipTests

# Etapa 2: Construcción de la imagen para producción
FROM eclipse-temurin:17-jre
WORKDIR /app

# Exponemos el puerto que usa tu aplicación
EXPOSE 8080

# Variables de entorno (estas pueden ser sobreescritas con --env-file)
ENV SPRING_PROFILES_ACTIVE=default

# Copiamos el JAR desde la etapa de compilación
COPY --from=build /app/target/*.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
