#FROM eclipse-temurin:17-jdk-alpine as builder
#WORKDIR application
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} application.jar
#RUN java -Djarmode=layertools -jar application.jar extract
#
#FROM eclipse-temurin:17-jdk-alpine
#WORKDIR application
#COPY --from=builder application/dependencies/ ./
#COPY --from=builder application/spring-boot-loader/ ./
#COPY --from=builder application/snapshot-dependencies/ ./
#COPY --from=builder application/application/ ./
#ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/workoutBot-0.0.1-SNAPSHOT.jar
RUN echo ${JAR_FILE}
RUN echo $(ls -1 build/libs)
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
