FROM gradle:9-openjdk:21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon
RUN chmod +x ./gradlew
RUN ./gradlew --no-daemon buildFatJar


From openjdk:21
EXPOSE 8081
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/DemoServer-all.jar
ENTRYPOINT ["java","-jar","/app/DemoServer-all.jar"]

