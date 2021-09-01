#!/bin/sh

docker compose build
docker tag supersonic_supersonic:latest $DOCKER_REGISTRY/supersonic
docker push $DOCKER_REGISTRY/supersonic