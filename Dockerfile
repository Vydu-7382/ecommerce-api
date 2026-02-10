# -------- BUILD STAGE --------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /ecommerce-api

# Copy Maven wrapper and pom first
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x mvnw
# Download dependencies (cached)
RUN ./mvnw dependency:go-offline

# Copy source code and build
COPY src src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /ecommerce-api

COPY --from=build /ecommerce-api/target/*.jar ecommerce-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ecommerce-api.jar"]
