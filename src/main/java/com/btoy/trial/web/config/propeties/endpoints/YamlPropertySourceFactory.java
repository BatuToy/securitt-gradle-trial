package com.btoy.trial.web.config.propeties.endpoints;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.util.Objects;
import java.util.Properties;

/*
 * @created 25/03/2026 ~~ 21:48
 * author: batu
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(@Nullable String name, EncodedResource resource) {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        final String fileName = Objects.requireNonNull(resource.getResource().getFilename(), "Resource File Name Could Not Be Null!");
        Properties properties = Objects.requireNonNull(factory.getObject());
        return new PropertiesPropertySource(fileName, properties);

    }
}
