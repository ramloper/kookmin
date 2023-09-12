FROM openjdk:11-jdk
ARG JAR_FILE=builds/libs/*.jar
ADD ${JAR_FILE} kookminServer.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "kookminServer.jar"]