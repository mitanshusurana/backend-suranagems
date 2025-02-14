FROM amazoncorretto:23 AS build

WORKDIR /app
COPY . .
RUN yum install -y findutils && \
    chmod +x gradlew && \
    ./gradlew bootJar --no-daemon

FROM amazoncorretto:23
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
