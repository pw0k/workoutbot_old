FROM eclipse-temurin:17-jdk as builder
WORKDIR application
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:17-jdk
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENV JAVA_OPTS="-Xmx256m"
#-XX:MaxRAMPercentage
#ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS", "org.springframework.boot.loader.launch.JarLauncher"]
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS  org.springframework.boot.loader.launch.JarLauncher"]