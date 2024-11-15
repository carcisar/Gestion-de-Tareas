#Usa una imagen base de Java JDK 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el script wait-for-it.sh al contenedor
COPY wait-for-it.sh /wait-for-it.sh

# Otorga permisos de ejecución al script
RUN chmod +x /wait-for-it.sh

# Copia el archivo .jar de tu aplicación en el contenedor
COPY target/cuaderno-gateway-service-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8090

# Comando de inicio que espera a que los servicios estén listos antes de ejecutar la aplicación
ENTRYPOINT ["/wait-for-it.sh", "eureka-server:8761", "--timeout=60", "--strict", "--", "/wait-for-it.sh", "config-server:8888", "--timeout=60", "--strict", "--", "java", "-jar", "app.jar"]

