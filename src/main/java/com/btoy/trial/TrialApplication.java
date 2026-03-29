package com.btoy.trial;

import com.btoy.trial.persistence.base.TriSimpleAuthJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.btoy.trial")
@EntityScan(basePackages = {"com.btoy.trial.persistence.entity"})
@EnableJpaRepositories(basePackages = {"com.btoy.trial.persistence.dao"}, repositoryBaseClass = TriSimpleAuthJpaRepositoryImpl.class)
public class TrialApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrialApplication.class, args);
    }

}
