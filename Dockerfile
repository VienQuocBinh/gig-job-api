FROM amazoncorretto:17
ARG JAVA_FILE_NAME
COPY $JAVA_FILE_NAME app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]