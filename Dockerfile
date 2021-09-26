FROM gradle:jdk15 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build && rm build/libs/*plain.jar

FROM openjdk:15.0-jdk-slim
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar","/app/app.jar"] 