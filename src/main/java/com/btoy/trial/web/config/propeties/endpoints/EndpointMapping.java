package com.btoy.trial.web.config.propeties.endpoints;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/*
 * @created 25/03/2026 ~~ 22:03
 * author: batu
 */
@Target(value=ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
public @interface EndpointMapping {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String value() default "";
}
