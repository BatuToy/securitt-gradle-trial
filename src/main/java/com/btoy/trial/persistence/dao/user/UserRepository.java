package com.btoy.trial.persistence.dao.user;

import com.btoy.trial.persistence.base.TriAuthSimpleJpaRepository;
import com.btoy.trial.persistence.entity.user.User;

import java.util.Optional;

public interface UserRepository extends TriAuthSimpleJpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
