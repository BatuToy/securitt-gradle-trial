package com.btoy.trial.persistence.base;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface TriAuthSimpleJpaRepository<@NonNull E extends AbstractEntity<I>, I> extends JpaRepository<@NonNull E,@NonNull I> {

    void softDeleteById(I id);

    E updateById(I id);

    E update(E entity);

    Optional<E> findByIdAndActive(I id);

    Optional<E> findOneByCriteria(Collection<FilterCriteria> filters);

    Optional<List<E>> findAllByCriteria(Collection<FilterCriteria> filters);

    Page<E> findAllByCriteriaPageable(Collection<FilterCriteria> filters, Pageable pageable);

}
