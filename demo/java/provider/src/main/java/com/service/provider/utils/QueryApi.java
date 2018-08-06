package com.service.provider.utils;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class QueryApi {
    private static Logger logger = LoggerFactory.getLogger(QueryApi.class);

    public static QueryApi INSTANCE = new QueryApi();

    private RestTemplate template = RestTemplateBuilder.create();

    public void send(String serviceName, String command) {
        String resp = template.getForObject(
                "cse://" + serviceName + "/provider/helloworld?name=" + command, String.class);
        logger.info("say hello name={}", resp);
    }
}
