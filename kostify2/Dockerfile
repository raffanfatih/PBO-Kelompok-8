# Stage 1: Build aplikasi dengan Maven & Java 21
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copy Maven wrapper dan pom.xml terlebih dahulu untuk cache dependencies
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copy source code dan build JAR
COPY src src
RUN ./mvnw clean package -DskipTests -B

# Stage 2: Runtime ringan dengan JRE 21
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Limit memori JVM agar aman di hosting free tier (512MB RAM)
ENTRYPOINT ["java", "-XX:+UseSerialGC", "-Xms128m", "-Xmx320m", "-jar", "app.jar"]