FROM frolvlad/alpine-oraclejdk8:slim

VOLUME /tmp
EXPOSE 8080

ADD *.jar /app.jar

CMD sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
