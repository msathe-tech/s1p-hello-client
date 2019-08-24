## Setup Docker Image
```./mvnw clean package```
```docker build -t msathepivotal/hello-client .```
```docker push msathepivotal/hello-client```

## Deploy on K8s
```kubectl create -f kubernetes/overlays/local/deployment.yaml -f kubernetes/overlays/local/service.yaml```
