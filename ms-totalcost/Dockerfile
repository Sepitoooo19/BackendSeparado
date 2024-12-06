FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-followrequest.jar
ENTRYPOINT ["java","-jar","/ms-followrequest.jar"]