package com.accountservice.accountservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MessageRestController {

    @Value("${propertyMessage:Hello world - Config Server is not working..Please check configuration }")
    private String message;

    @RequestMapping("/getMessage")
    String getMessage() {
        return this.message;
    }
}