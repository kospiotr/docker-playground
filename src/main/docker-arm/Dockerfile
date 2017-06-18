FROM dordoka/rpi-java8

VOLUME /tmp
EXPOSE 8080

ADD *.jar /app.jar

CMD sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "sleep 2 && java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
