package com.btoy.trial.model.base;

import lombok.AllArgsConstructor;
import lombok.Setter;

/*
 * @created 28/03/2026 ~~ 23:37
 * author: batu
 */
@Setter
public abstract class TriAuthAbstractVersionedModel<I> implements VersionedModel, BaseModel<I> {

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
