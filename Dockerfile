FROM gradle:8-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:21
EXPOSE 8081:8081
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/DemoServer.jar
ENTRYPOINT ["java","-jar","/app/DemoServer.jar","-port=8081"]