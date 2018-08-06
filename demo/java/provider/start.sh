#!/bin/bash

# CMDVAR="-Djava.security.egd=file:/dev/./urandom","java -agentlib:jdwp=transport=dt_socket,address=0:8000,server=y,suspend=n -jar"
while :; do

java $CMDVAR \
-Dcse.service.registry.address="${REGISTRY:-"http://127.0.0.1:30100"}" \
-Dcse.rest.address="0.0.0.0:${PORT:-0}" \
-Dservice_description.name="${TYPE:-"provider"}" \
-DAPPLICATION_ID="${APP:-"default"}" \
-jar ./provider-0.0.1-SNAPSHOT.jar

if [ "${TYPE}"x == "web"x ]; then
    exit 1
fi

done