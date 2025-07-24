# Start from a lightweight JDK image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy the built jar into the container
COPY target/website-0.0.1-SNAPSHOT.jar app.jar

# Optional: copy .env (for debug only – not needed in container runtime)
# COPY .env .env

# Expose port used by Spring Boot (default 8080)
EXPOSE 8080

# Start the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]

# # In your Dockerfile, near other COPY commands
# COPY tidb-ca.pem /app/tidb-ca.pem