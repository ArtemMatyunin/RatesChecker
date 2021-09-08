FROM openjdk:8-jdk-alpine
COPY build/libs/RatesChecker-0.0.1-SNAPSHOT.jar RatesChecker-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/RatesChecker-0.0.1-SNAPSHOT.jar"]
