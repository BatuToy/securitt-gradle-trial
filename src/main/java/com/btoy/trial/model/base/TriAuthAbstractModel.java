package com.btoy.trial.model.base;

import jakarta.persistence.MappedSuperclass;
import lombok.Setter;

/*
 * @created 28/03/2026 ~~ 23:37
 * author: batu
 */
@Setter
@MappedSuperclass
public class TriAuthAbstractModel<I> implements BaseModel<I> {

    private I id;

    private Integer version;

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public I getId() {
        return id;
    }
}
