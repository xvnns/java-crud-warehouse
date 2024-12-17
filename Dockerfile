FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ADD build/libs/crud-warehouse-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","/app.jar"]