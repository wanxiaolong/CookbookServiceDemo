FROM openjdk:8
MAINTAINER wanxiaolong<wanxiaolong100@163.com>
ENV PORT=8080
ENV DEBUG_PORT=5000
RUN mkdir -p /home/java/logs && chmod -R a+rw /home/java/logs
COPY target/CookbookServiceDemo-1.0-SNAPSHOT.jar /home/java
COPY application.yml /home/java
EXPOSE $PORT
EXPOSE $DEBUG_PORT
WORKDIR /home/java
ENTRYPOINT ["java","-jar","CookbookServiceDemo-1.0-SNAPSHOT.jar","server","application.yml"]
