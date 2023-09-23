package com.k8s.springdeployement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello K8s,\n" +
                "💻💻💻💻💻\n" +
                "🐥🐥🐥🐥🐥🐥🐥\n" +
                " \n🚨🚨";
    }
}
