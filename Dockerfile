FROM openjdk:21-oracle
COPY target/*.jar nonotion-service.jar
ENTRYPOINT ["java","-jar","/nonotion-service.jar"]