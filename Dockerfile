# Use Java 17 JDK as base image
FROM eclipse-temurin:17-jdk

# Set environment variable for optional JVM options
ENV JAVA_OPTS=""

# Set working directory inside the container
WORKDIR /app

# Copy the pre-built JAR from target folder
COPY target/spring-petclinic-2.1.0.BUILD-SNAPSHOT.jar app.jar

# Expose port your Spring Boot app runs on
EXPOSE 8085

# Run the Spring Boot jar
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

