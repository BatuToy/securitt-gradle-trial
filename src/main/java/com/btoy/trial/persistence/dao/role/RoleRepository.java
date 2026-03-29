package com.btoy.trial.persistence.dao.role;

import com.btoy.trial.persistence.base.TriAuthSimpleJpaRepository;
import com.btoy.trial.persistence.entity.role.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends TriAuthSimpleJpaRepository<Role, Long> {
}
