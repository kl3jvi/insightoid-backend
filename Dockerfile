FROM openjdk:21
EXPOSE 8080:8080
RUN mkdir /app
COPY /build/libs/insightoid-backend-all.jar /app/insightoid-backend.jar
ENTRYPOINT ["java", "-jar", "/app/insightoid-backend.jar"]
