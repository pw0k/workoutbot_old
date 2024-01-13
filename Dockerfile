# Use an OpenJDK 17 base image
FROM openjdk:17-jdk-alpine

# Add Maintainer Info
LABEL maintainer="pw"

#ENV JAVA_OPTS="-XX:MaxDirectMemorySize=64M -XX:MaxMetaspaceSize=96M -XX:ReservedCodeCacheSize=64M -Xss256K"
ENV JAVA_OPTS="-XX:MaxDirectMemorySize=10M -XX:MaxMetaspaceSize=96M -XX:ReservedCodeCacheSize=64M -Xss256K"


# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=build/libs/workoutBot-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]