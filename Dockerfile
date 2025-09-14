# Imagen base con Java 17, cambia si usas otra versión
FROM openjdk:17-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el jar compilado al contenedor (ajusta el nombre del jar si es distinto)
COPY target/corte-automatico-0.0.1-SNAPSHOT.jar  app.jar

# Copiamos el archivo de configuración al contenedor
COPY .env .env

# Expone el puerto que tu app usa (normalmente 8080)
EXPOSE 8000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]