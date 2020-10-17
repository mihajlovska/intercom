#Build Java App
FROM gradle:jdk11 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew build

#Build & Run Java Jar File
FROM openjdk:11.0.4-jre-slim
EXPOSE 8080
COPY --from=builder /home/gradle/src/build/libs/test-0.0.1.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-jar", "test-0.0.1.jar"]