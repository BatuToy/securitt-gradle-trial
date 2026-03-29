package com.btoy.trial.persistence.dao.authority;

import com.btoy.trial.persistence.base.TriAuthSimpleJpaRepository;
import com.btoy.trial.persistence.entity.authority.Authority;
import org.springframework.stereotype.Repository;

/*
 * @created 16/03/2026 ~~ 20:15
 * author: batu
 */
@Repository
public interface AuthorityRepository extends TriAuthSimpleJpaRepository<Authority, Long> {
}
