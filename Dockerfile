# ----------- STAGE 1: Build the app -------------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ----------- STAGE 2: Run the app ----------------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/website-0.0.1-SNAPSHOT.jar app.jar

# Copy the TiDB CA certificate if needed
COPY src/main/resources/tidb-ca.pem /app/tidb-ca.pem

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

