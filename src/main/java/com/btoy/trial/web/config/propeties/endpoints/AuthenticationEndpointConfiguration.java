package com.btoy.trial.web.config.propeties.endpoints;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*
 * @created 29/03/2026 ~~ 20:14
 * author: batu
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "trial-app.endpoints.authentication")
public class AuthenticationEndpointConfiguration {

    private String base;

    private String login;

    private String register;

    private String logout;

}
