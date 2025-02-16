FROM amazoncorretto:17-alpine3.21-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY startContainer.sh /script/
RUN ["chmod", "+x", "/script/startContainer.sh"]
# making the  script executable

ENTRYPOINT ["/bin/sh","/script/startContainer.sh"]