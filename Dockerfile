FROM openjdk:16
VOLUME /tmp
EXPOSE 8080
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar spring-boot-angular-1.0.jar
ENTRYPOINT ["java","-jar","/spring-boot-angular-1.0.jar"]
