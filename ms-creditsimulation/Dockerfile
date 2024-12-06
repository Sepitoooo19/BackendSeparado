FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-creditsimulation.jar
ENTRYPOINT ["java","-jar","/ms-creditsimulation.jar"]