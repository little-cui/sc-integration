package com.service.provider.controller;


import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

public class TestProvider {

    ProviderDelegate providerDelegate = new ProviderDelegate();


    @Test
    public void testhelloworld() throws UnknownHostException {

        String expactReturnValue = "127.0.0.1: hello"; // You should put the expect String type value here.

        String returnValue = providerDelegate.helloworld("hello");

        assertEquals(expactReturnValue, returnValue);
    }

}