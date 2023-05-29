FROM openjdk:11.0.12-oraclelinux8
ADD target/personalproject-0.0.1-SNAPSHOT.jar foodaholic
ENTRYPOINT ["java","-jar","foodaholic"]
EXPOSE 8080