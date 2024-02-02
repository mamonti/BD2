#!/bin/sh
docker rm -f nonotion-service mongo-db
if docker image inspect bd2-nonotion-service > /dev/null 2>&1; then
    docker image rm bd2-nonotion-service
fi
call .\mvnw.cmd clean package -Dmaven.test.skip=false
docker compose up -d