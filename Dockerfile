FROM tomcat:9.0.56-jdk11

ADD target/lab2-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/lab2-0.0.1-SNAPSHOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
