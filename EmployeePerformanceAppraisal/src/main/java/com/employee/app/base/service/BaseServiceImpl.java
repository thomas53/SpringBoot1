package com.employee.app.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource("classpath:message.properties")
public class BaseServiceImpl {

    @Autowired
    private Environment env;

    protected String generateMessage(String responseCode) {
        String message = env.getProperty("message.error.code." + responseCode);

        if (message==null || message.equals("")) {
            message = "Pesan Error Tidak Diketahui.";
        }

        return message;
    }
}
