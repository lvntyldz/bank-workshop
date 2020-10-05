package com.ba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class InfoController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private Environment env;

    @GetMapping("info-profile")
    public String getProfile() {
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        if (CollectionUtils.isEmpty(profiles)) {
            return null;
        }

        return profiles.get(0);
    }

    @GetMapping("info-server")
    public String getServerPort() {
        return serverPort;
    }
}
