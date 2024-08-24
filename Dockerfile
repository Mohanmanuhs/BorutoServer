FROM gradle:latest AS BUILD_STAGE
WORKDIR /tmp
COPY gradle gradle
COPY build.gradle.kts gradle.properties settings.gradle.kts gradlew ./
COPY src src
RUN ./gradlew --no-daemon buildFatJar

FROM openjdk:21-jdk-slim
EXPOSE 8081:8081
RUN mkdir /app
COPY --from=BUILD_STAGE /tmp/build/libs/*-all.jar /app/DemoServer.jar
ENTRYPOINT ["java","-Xlog:gc+init","-XX:+PrintCommandLineFlags","-jar","/app/DemoServer.jar"]