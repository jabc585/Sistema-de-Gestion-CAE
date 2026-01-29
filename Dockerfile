FROM maven:3.8.6-eclipse-temurin-11 AS build
WORKDIR /app

ENV MAVEN_OPTS="-Djava.net.preferIPv4Stack=true"
ENV JAVA_TOOL_OPTIONS="-Djava.net.preferIPv4Stack=true"

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:11-jre
ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y \
    libxext6 libxrender1 libxtst6 libxi6 \
    libfreetype6 fontconfig libx11-6 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY --from=build /app/target/dashboard-ahorro-energetico-1.0-SNAPSHOT.jar app.jar
COPY data ./data

ENV DISPLAY=:0
ENV JAVA_TOOL_OPTIONS="-Djava.awt.headless=false"

CMD ["java", "-jar", "app.jar"]
