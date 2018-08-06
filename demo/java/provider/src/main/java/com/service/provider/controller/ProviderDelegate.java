package com.service.provider.controller;

import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Component
public class ProviderDelegate {

    public String helloworld(String name) throws UnknownHostException {
        InetAddress ia = InetAddress.getLocalHost();
        return ia.getHostAddress() + ": " + name;
    }
}
