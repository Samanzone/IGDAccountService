FROM openjdk:17-oracle

# Maintainer
MAINTAINER "Saman N Perera"

RUN mkdir /app
ADD build/libs/igd-account-service-*.jar ./api.jar
ADD environment/docker/docker-config/run.sh /app/run.sh
RUN chmod +x /app/run.sh
EXPOSE 8080

ENTRYPOINT ["/app/run.sh"]
