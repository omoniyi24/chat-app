# Use Maven to build the application
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

# Copy the pom.xml and src directory (or whatever contains your source code)
COPY pom.xml .
COPY src/ src/

# Use Maven to package our application. The resulting JAR will be at /app/target/chat-app.jar
RUN mvn package

# Now, use Java 17 to run our application
FROM openjdk:17

WORKDIR /app

# Copy the resulting JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Set necessary environment variables
ENV _JAVA_OPTIONS="-Xmx512m -Xms256m"
ENV SPRING_PROFILES_ACTIVE="dev"

# Run the application
CMD ["java", "-jar", "app.jar"]
