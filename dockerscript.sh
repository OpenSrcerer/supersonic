#!/bin/sh

docker build -t supersonic .
docker tag supersonic:latest $DOCKER_REGISTRY/supersonic
docker push $DOCKER_REGISTRY/supersonic