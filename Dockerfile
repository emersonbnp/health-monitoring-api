# Step : Package image
FROM openjdk:8-jre-alpine
CMD exec java $JAVA_OPTS -jar /app/my-app.jar
WORKDIR /build
COPY target/*.jar /app/my-app.jar