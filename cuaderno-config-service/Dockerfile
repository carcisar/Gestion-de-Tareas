# Usa una imagen base de Java JDK 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el script wait-for-it.sh al contenedor
COPY wait-for-it.sh /wait-for-it.sh

# Otorga permisos de ejecución al script
RUN chmod +x /wait-for-it.sh

# Copia el archivo .jar de tu aplicación en el contenedor
COPY target/cuaderno-config-service-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8888

# Comando de inicio que espera a que el eureka-server esté listo antes de ejecutar la aplicación
ENTRYPOINT ["/wait-for-it.sh", "eureka-server:8761", "--timeout=60", "--strict", "--", "java", "-jar", "app.jar"]
