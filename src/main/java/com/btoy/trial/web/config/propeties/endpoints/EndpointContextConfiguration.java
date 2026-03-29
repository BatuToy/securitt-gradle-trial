package com.btoy.trial.web.config.propeties.endpoints;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
 * @created 25/03/2026 ~~ 21:59
 * author: batu
 */
@Configuration
@PropertySource(value = "classpath:tri-auth-context-servlet.yaml", factory = YamlPropertySourceFactory.class)
public class EndpointContextConfiguration {
}
