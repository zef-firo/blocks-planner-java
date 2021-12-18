## building the jar archive with maven
FROM maven:3.8.4-jdk-8-slim as builder

ENV HOME=/home/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME

# 1. add pom.xml and checkstyle.xml here
ADD blocksplanner/pom.xml $HOME
ADD blocksplanner/checkstyle.xml $HOME

# 2. start downloading dependencies
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean"]

# 3. add all source code and start compiling
ADD blocksplanner/. $HOME

RUN ["mvn", "-B", "install"]

##execute the compiled jar
FROM openjdk:18-oraclelinux8

COPY --from=builder /home/usr/app/target/blocksplanner-1.0-SNAPSHOT.jar /home/blocksplanner-1.0-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/home/blocksplanner-1.0-SNAPSHOT.jar"]
