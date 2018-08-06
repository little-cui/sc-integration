FROM openjdk:8-jre-alpine

WORKDIR /home/apps/
ADD target/provider-0.0.1-SNAPSHOT.jar .

ADD start.sh .

ENTRYPOINT ["sh", "-x", "/home/apps/start.sh"]