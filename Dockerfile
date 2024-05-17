FROM openjdk:22-oracle
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]