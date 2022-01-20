FROM openjdk:11
WORKDIR /app
COPY ./target/activity-notebook-0.0.1.jar .
CMD ["java", "-jar", "activity-notebook-0.0.1.jar"]