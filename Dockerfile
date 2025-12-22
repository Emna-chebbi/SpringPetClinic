# Use Java 17 JDK as base image
FROM eclipse-temurin:17-jdk

# Set environment variable for optional JVM options
ENV JAVA_OPTS=""

# Set working directory inside the container
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy pom.xml first to leverage Docker layer caching
COPY pom.xml .

# Download all dependencies (offline mode)
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the project, skip tests to avoid Selenium errors
RUN mvn clean package -DskipTests

# Expose port your Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot jar
CMD ["sh", "-c", "java $JAVA_OPTS -jar target/spring-petclinic-2.1.0.BUILD-SNAPSHOT.jar"]
