docker rm -f nonotion-service
docker image rm bd2-nonotion-service
call .\mvnw.cmd clean package -Dmaven.test.skip=false
docker compose up -d