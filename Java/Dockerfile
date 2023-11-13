FROM openjdk:11

WORKDIR /app


COPY src /app/src


COPY docker.entrypoint.sh /app/docker.entrypoint.sh

COPY data.txt /app/data.txt


RUN chmod +x /app/docker.entrypoint.sh


ENTRYPOINT ["/app/docker.entrypoint.sh"]

