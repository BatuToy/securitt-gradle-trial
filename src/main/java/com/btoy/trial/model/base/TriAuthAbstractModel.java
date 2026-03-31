package com.btoy.trial.model.base;

/*
 * @created 31/03/2026 ~~ 19:53
 * author: batu
 */

import jakarta.persistence.MappedSuperclass;
import lombok.Setter;

@Setter
public class TriAuthAbstractModel<I> implements BaseModel<I> {

    private I id;

    @Override
    public I getId() {
        return id;
    }
}
