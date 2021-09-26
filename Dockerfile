FROM openjdk:11
LABEL maintainer="jane.net"
ADD target/mytask-week-9-0.0.1-SNAPSHOT.jar week-9-task.jar
ENTRYPOINT ["java", "-jar", "week-9-task.jar"]
