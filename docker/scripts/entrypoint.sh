#!/bin/bash

if [[ -n ${SERVER_PORT} ]]; then
  JAVA_OPTS="-Dserver.port=${SERVER_PORT} ${JAVA_OPTS}"
fi

if [[ -n "$APPLICATION_NAME" ]]; then
  exec java $JAVA_OPTS -jar /openexchange/${APPLICATION_NAME}.jar
else
  echo "ERROR: No application file to start. Exiting."
  exit 1
fi