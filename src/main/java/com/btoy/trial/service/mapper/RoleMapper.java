package com.btoy.trial.service.mapper;

import com.btoy.trial.model.security.AuthorityModel;
import com.btoy.trial.model.security.RoleModel;
import com.btoy.trial.persistence.entity.role.Role;
import com.btoy.trial.web.security.util.ObjectUtils;
import org.springframework.stereotype.Component;

/*
 * @created 01/04/2026 ~~ 19:58
 * author: batu
 */
@Component
public class RoleMapper {

    private final AuthorityMapper authorityMapper;
    private final UserMapper userMapper;

    public RoleMapper(AuthorityMapper authorityMapper, UserMapper userMapper) {
        this.authorityMapper = authorityMapper;
        this.userMapper = userMapper;
    }

    public RoleModel toModel(Role role) {
        return RoleModel.builder()
                .authorities(role.getAuthorities().stream().map(authorityMapper::toModel).toList())
                . build();
    }

    public Role toEntity(RoleModel model) {
        return Role.builder()
                .id(ObjectUtils.nvl(model.getId()))
                .code(model.getCode())
                .name(model.getName())
                .authorities(null)
                .users(null)
                .build();
    }
}
