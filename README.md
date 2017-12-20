# sc-integration

A simple demo to deploy ServiceCenter with prometheus.

## Quick start


```bash
git clone https://github.com/little-cui/sc-integration.git
cd sc-integration
docker-compose up
```

## Import dashboard

Download the template from ServiceComb.io

```bash
wget https://github.com/ServiceComb/service-center/blob/master/integration/health-metrics-grafana.json
```

Import the ServiceCenter dashboard template in Grafana.

![](homepage.png)
