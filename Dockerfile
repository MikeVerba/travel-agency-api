FROM openjdk:8

#RUN "mvn clean install"
#
## Refer to Maven build -> finalName
#ARG JAR_FILE=target/travel-agency-api.jar
#
## cd /opt/app
#WORKDIR /opt/app
#
## cp target/spring-boot-web.jar /opt/app/app.jar
#COPY ${JAR_FILE} app.jar
#
## java -jar /opt/app/app.jar
#ENTRYPOINT ["java","-jar","app.jar"]


#
# Build stage
#
FROM maven:3.6.0-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jre-slim
COPY --from=build /home/app/target/travel-agency-api.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
