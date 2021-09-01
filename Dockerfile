#
# Build & Package
#
FROM openjdk:16.0.2-jdk as build

WORKDIR /buildapp

COPY . .

RUN ./gradlew shadowJar

#
# Run
#
FROM openjdk:16.0.2-jdk

ARG JAR_FILE=./build/libs/*-all.jar

WORKDIR /app

COPY --from=build /buildapp/${JAR_FILE} supersonic.jar

ENTRYPOINT ["java", "-jar", "supersonic.jar"]
