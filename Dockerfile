FROM gradle:7.4-jdk11 as builder

WORKDIR /app

COPY . .

RUN gradle clean bootJar

FROM openjdk:11-jre-slim as runtime

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "app.jar"]