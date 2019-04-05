FROM java:8u111-jre
COPY target/*.jar stock-0.0.1-SNAPSHOT.jar
COPY key key
ENTRYPOINT ["java","-jar","/stock-0.0.1-SNAPSHOT.jar"]