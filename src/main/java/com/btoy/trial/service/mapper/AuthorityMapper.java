package com.btoy.trial.service.mapper;

import com.btoy.trial.model.security.AuthorityModel;
import com.btoy.trial.persistence.entity.authority.Authority;
import org.springframework.stereotype.Component;

/*
 * @created 01/04/2026 ~~ 19:58
 * author: batu
 */
@Component
public class AuthorityMapper {

    public AuthorityModel toModel(Authority authority) {
        return AuthorityModel.builder().build();
    }

    public Authority toEntity(AuthorityModel model) {
        return Authority.builder()
                .name(model.getName())
                .code(model.getCode())
                .build();
    }
}
