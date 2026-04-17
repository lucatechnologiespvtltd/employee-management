FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY employee-management ./employee-management

WORKDIR /app/employee-management

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

CMD ["sh", "-c", "java -jar target/*.jar"]