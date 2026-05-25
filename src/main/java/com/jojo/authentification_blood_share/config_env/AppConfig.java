package com.jojo.authentification_blood_share.config_env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${app.secret.passwordgov}")
    private String secretPassword;

    public String getSecretPassword() {
        return secretPassword;
    }
}
