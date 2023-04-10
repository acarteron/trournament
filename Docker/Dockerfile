FROM gradle:8.0.2-jdk19-alpine

MAINTAINER Adrien Carteron <acarteron@cha-sam.re>

ENV SCHEME=''
ENV HOST=''
ENV PORT=''
ENV NAME=''
ENV COLLECTION=''

RUN apk update && apk add g++ &&\
cd /opt &&\
git clone -b master https://git.cha-sam.re/acarteron/Tournament.git &&\
cd Tournament &&\
    gradle build &&\
    unzip build/distributions/playerRank-0.0.1.zip -d /opt &&\
apk del unzip

EXPOSE 8080
ENTRYPOINT /opt/playerRank-0.0.1/bin/playerRank