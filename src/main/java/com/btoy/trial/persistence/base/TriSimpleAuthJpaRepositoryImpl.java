package com.btoy.trial.persistence.base;

import com.btoy.trial.constants.Log;
import com.btoy.trial.persistence.exception.TriAuthEntityNotFoundException;
import com.btoy.trial.persistence.exception.TriAuthUpdateFailedException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.btoy.trial.constants.Persistence.COL_IS_ACTIVE;
import static com.btoy.trial.persistence.base.AbstractEntity.COL_ID;
import static com.btoy.trial.persistence.base.FilterCriteria.Operation.*;

/*
 * @created 28/03/2026 ~~ 17:13
 * author: batu
 *
 */
public class TriSimpleAuthJpaRepositoryImpl<E extends AbstractEntity<I>, I> extends SimpleJpaRepository<@NonNull E, @NonNull I>
        implements TriAuthSimpleJpaRepository<E, I> {

    private final EntityManager manager;
    private final Class<E> domainClazz;
    private final CriteriaBuilder cb;


    public TriSimpleAuthJpaRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.manager = entityManager;
        cb = this.manager.getCriteriaBuilder();
        this.domainClazz = entityInformation.getJavaType(); // Capturing Generic Class on Runtime.
    }

    @Override
    @Transactional
    public void softDeleteById(I id) {
        E entity = findById(id).orElseThrow(TriAuthEntityNotFoundException::new);
        if (entity instanceof DeActivable deActivableEntity) {
            if (deActivableEntity.active()) {
                deActivableEntity.deActivate();
                manager.merge(deActivableEntity);
            } else {
                Log.LOGGER.info("UPDATE FAILED: Entity Was Already DeActivated!");
                throw new TriAuthUpdateFailedException();
            }
        }
    }

    @Override
    public Optional<E> findById(I id) {
        CriteriaQuery<E> cq = cb.createQuery(domainClazz);
        Root<E> root = cq.from(domainClazz);
        Set<FilterCriteria> filters = Set.of(
                FilterCriteria.of(COL_ID, List.of(id), EQ),
                FilterCriteria.of(COL_IS_ACTIVE, List.of(Boolean.TRUE), EQ)
        );
        Set<Predicate> predicates = (Set<Predicate>) buildPredications(root, filters);
        return executeSingleResultQuery(cq, predicates);
    }

    @Override
    @Transactional
    public E updateById(I id) {
        E entity = findById(id).orElseThrow(TriAuthEntityNotFoundException::new);
        manager.merge(entity);
        return findById(id).orElseThrow(TriAuthEntityNotFoundException::new);
    }

    @Override
    @Transactional
    public E update(E updateModel) {
        findById(updateModel.getId()).orElseThrow(TriAuthEntityNotFoundException::new);
        return manager.merge(updateModel);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findByIdAndActive(I id) {
        return findById(id);
    }

    @Override
    public Optional<E> findOneByCriteria(Collection<FilterCriteria> filters) {
        CriteriaQuery<E> cq = cb.createQuery(domainClazz);
        Root<E> root = cq.from(domainClazz);
        Collection<Predicate> predicates = buildPredications(root, filters);
        return executeSingleResultQuery(cq, predicates);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<E>> findAllByCriteria(Collection<FilterCriteria> filters) {
        CriteriaQuery<E> cq = cb.createQuery(domainClazz);
        Root<E> root = cq.from(domainClazz);
        Collection<Predicate> predicates = buildPredications(root, filters);
        return executeMultiResultQuery(cq, predicates);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<E> findAllByCriteriaPageable(Collection<FilterCriteria> filters, Pageable pageable) {
        CriteriaQuery<E> cq = cb.createQuery(domainClazz);
        Root<E> root = cq.from(domainClazz);
        Collection<Predicate> predicates = buildPredications(root, filters);
        Optional<List<E>> optionalContent = executeMultiResultQuery(cq, predicates);
        List<E> content = optionalContent.orElse(List.of());
        long total = countByPredicate(predicates);
        return new PageImpl<>(content, pageable, total);
    }

    @SuppressWarnings("unchecked")
    private Optional<E> executeSingleResultQuery(CriteriaQuery<E> cq, Collection<Predicate> predicates) throws ClassCastException {
        Predicate pQuery = cb.and((Predicate[]) predicates.toArray());
        CriteriaQuery<E> criteriaQuery = cq.where(pQuery);
        TypedQuery<E> query = manager.createQuery(criteriaQuery);
        return Optional.ofNullable(query.getSingleResult());
    }

    @SuppressWarnings("unchecked")
    private Optional<List<E>> executeMultiResultQuery(CriteriaQuery<E> cq, Collection<Predicate> predicates) {
        Predicate pQuery = cb.and(predicates.toArray(new Predicate[0]));
        CriteriaQuery<E> criteriaQuery = cq.where(pQuery);
        TypedQuery<E> query = manager.createQuery(criteriaQuery);
        return Optional.ofNullable(query.getResultList());
    }

    private Collection<Predicate> buildPredications(Root<E> root, Collection<FilterCriteria> filters) {
        return filters.stream().map(f -> toPredicate(root, f)).toList();
    }

    private Predicate toPredicate(Root<E> root, FilterCriteria filter) {
        Object data = filter.getData().getFirst();
        return switch (filter.getOperation()) {
            case EQ -> cb.equal(root.get(filter.getCriteriaName()), data);
            case NEQ -> cb.notEqual(root.get(filter.getCriteriaName()), data);
            case GT -> cb.greaterThan(root.get(filter.getCriteriaName()),(Comparable) data);
            case LT -> cb.lessThan(root.get(filter.getCriteriaName()), (Comparable) data);
            case IN -> root.get(filter.getCriteriaName()).in(data);
            case LIKE -> cb.like(root.get(filter.getCriteriaName()), LIKE.getValue() + data + LIKE.getValue());
            case BETWEEN -> cb.between(root.get(filter.getCriteriaName()),
                    (Comparable) filter.getData().getFirst(),
                    (Comparable) filter.getData().getLast());
        };
    }

    private long countByPredicate(Collection<Predicate> predicates) {
        Predicate pQuery = cb.and(predicates.toArray(new Predicate[0]));
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<E> root = cq.from(domainClazz);
        CriteriaQuery<Long> typedQuery = cq.select(cb.count(root)).where(pQuery);
        return manager.createQuery(typedQuery).getSingleResult();
    }

}
