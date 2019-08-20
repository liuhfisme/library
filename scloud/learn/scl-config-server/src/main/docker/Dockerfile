FROM 10.8.7.63/library/openjdk:8-alpine-withfont

VOLUME /bpo
EXPOSE 9092
EXPOSE 8092

ADD bpo-esbmp-be.jar bpo-esbmp-be.jar

CMD exec java -Djava.security.egd=file:/dev/./urandom -Duser.timezone=Asia/ShangHai $PARAM -jar /bpo-esbmp-be.jar
