FROM openjdk:17.0.2-slim

ARG jarFile
ARG dbConnection

ENV JAR_FILE=${jarFile}
ENV DB_CONNECTION=${dbConnection}

RUN export DEBIAN_FRONTEND=noninteractive

ADD ./build/libs/${jarFile} /app/

ENTRYPOINT [ "sh", "-c", "java -Xmx1g -jar /app/${JAR_FILE} --mongodb.uri=${DB_CONNECTION}" ]