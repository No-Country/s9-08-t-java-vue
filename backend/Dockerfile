#base docker image
FROM openjdk:17
LABEL maintainer="gabiezzi"
ADD target/movenow-0.0.1-SNAPSHOT.jar springboot-docker-demo.jar
ENTRYPOINT ["java", "-jar" , "springboot-docker-demo.jar"]
