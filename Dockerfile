FROM java:8
MAINTAINER duyunzelx@outlook.com
ADD commentservice.jar /app.jar
EXPOSE 8883
ENTRYPOINT ["/usr/bin/java", "-jar", "app.jar"]