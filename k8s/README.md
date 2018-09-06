# sc-integration

A simple demo to upgrade ServiceCenter with helm command in Kubernetes cluster.

## build

```bash
docker tag servicecomb/service-center:latest littlecui/service-center:latest
docker push littlecui/service-center:latest
docker tag servicecomb/scfrontend:latest littlecui/scfrontend:latest
docker push littlecui/scfrontend:latest

cd demo/java/provider
mvn clean package docker:push
```

## install

```bash
helm install --name servicecomb \
 --set sc.image.repository=littlecui/service-center \
 --set frontend.image.repository=littlecui/scfrontend \
 --set sc.image.pullPolicy=Always \
 --set frontend.image.pullPolicy=Always \
 service-center/

helm install --name test \
 --set provider.image.repository=littlecui/sc-provider \
 --set consumer.image.repository=littlecui/sc-provider \
 --set web.image.repository=littlecui/sc-provider \
 --set provider.image.pullPolicy=Always \
 --set consumer.image.pullPolicy=Always \
 --set web.image.pullPolicy=Always \
 sc-test/
```

## upgrade

```bash
helm upgrade \
 --set sc.image.repository=littlecui/service-center \
 --set frontend.image.repository=littlecui/scfrontend \
 --set sc.image.pullPolicy=Always \
 --set frontend.image.pullPolicy=Always \
 servicecomb service-center/
 
helm upgrade \
 --set provider.image.repository=littlecui/sc-provider \
 --set consumer.image.repository=littlecui/sc-provider \
 --set web.image.repository=littlecui/sc-provider \
 --set provider.image.pullPolicy=Always \
 --set consumer.image.pullPolicy=Always \
 --set web.image.pullPolicy=Always \
 test sc-test/
```

## clean up

```bash
helm delete --purge servicecomb

helm delete --purge test
```
