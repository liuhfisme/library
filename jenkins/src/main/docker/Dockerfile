FROM  openjdk:8-jdk-alpine
VOLUME  /tmp
ADD  jenkins.jar jenkins.jar
ENTRYPOINT  ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/jenkins.jar"]