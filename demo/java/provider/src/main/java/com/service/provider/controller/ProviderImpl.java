package com.service.provider.controller;


import com.service.provider.service.TestService;
import com.service.provider.service.WebService;
import com.service.provider.utils.QueryApi;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.core.MediaType;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.CseSpringDemoCodegen", date = "2018-06-26T08:35:29.832Z")

@RestSchema(schemaId = "provider")
@RequestMapping(path = "/provider", produces = MediaType.APPLICATION_JSON)
public class ProviderImpl {

    @Autowired
    private ProviderDelegate userProviderDelegate;


    @RequestMapping(value = "/helloworld",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public String helloworld(@RequestParam(value = "name", required = true) String name) throws Exception {
        String t = System.getenv("TYPE");
        if (t != null) {
            switch (t) {
                case "consumer":
                    QueryApi.INSTANCE.send("provider", name);
                case "web":
                    if (!WebService.INSTANCE.test()) {
                        throw new InvocationException(400, "Bad Request", "test failed");
                    }
                default:
                    break;
            }
        }
        TestService.INSTANCE.setTask(name);
        return userProviderDelegate.helloworld(name);
    }

}
