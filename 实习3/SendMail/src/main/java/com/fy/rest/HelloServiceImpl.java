package com.fy.rest;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {

    public String say(String name) {
        return "hello " + name;
    }
}
