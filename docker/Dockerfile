FROM eclipse-temurin:21.0.1_12-jre

LABEL maintainer="OpenExchange.io"

ENV APPLICATION_PORT=8080 \
    APPLICATION_NAME=svc-candidate-api

VOLUME /tmp
COPY target/*.jar /openexchange/${APPLICATION_NAME}.jar
COPY scripts/entrypoint.sh /openexchange/entrypoint.sh

RUN chmod +x /openexchange/entrypoint.sh

EXPOSE ${APPLICATION_PORT}

RUN addgroup --system --gid 1002 app && adduser --system --uid 1002 --gid 1002 appuser
USER 1002

ENTRYPOINT ["/openexchange/entrypoint.sh"]