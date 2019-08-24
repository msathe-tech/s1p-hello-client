#!/usr/bin/env bash
./mvnw clean package
docker build -t msathepivotal/hello-client .
docker push msathepivotal/hello-client
