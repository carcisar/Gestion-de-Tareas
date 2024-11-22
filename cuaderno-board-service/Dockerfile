FROM openjdk:17-jdk-slim
WORKDIR /app
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
COPY target/cuaderno-board-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["/wait-for-it.sh", "eureka-server:8761", "--", "/wait-for-it.sh", "config-server:8888", "--", "/wait-for-it.sh", "mariadb:3306", "--", "java", "-jar", "app.jar"]
