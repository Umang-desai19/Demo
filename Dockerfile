FROM openjdk:21-jdk
EXPOSE 8056

LABEL authors="valtechadmin"

ARG JAR_FILE=target/chargingStationService-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]