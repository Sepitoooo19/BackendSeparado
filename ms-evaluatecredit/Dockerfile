FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-evaluatecredit.jar
ENTRYPOINT ["java","-jar","/ms-evaluatecredit.jar"]