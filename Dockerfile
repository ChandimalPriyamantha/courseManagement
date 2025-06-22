FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
ADD target/courseManagement-0.0.1-SNAPSHOT.jar courseManagement-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/courseManagement-0.0.1-SNAPSHOT.jar"]