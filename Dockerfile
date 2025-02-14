FROM amazoncorretto:23 AS build

COPY . .
RUN ./gradlew bootJar --no-daemon

FROM amazoncorretto:23

EXPOSE 8080

COPY --from=build /build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
