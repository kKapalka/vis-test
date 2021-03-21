FROM openjdk:11-jre-slim
VOLUME /tmp
EXPOSE 8080
ADD build/libs/test-0.0.1-SNAPSHOT.jar test-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","test-0.0.1-SNAPSHOT.jar"]