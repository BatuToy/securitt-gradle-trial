package com.btoy.trial.model.base;

import lombok.Getter;
import lombok.Setter;

/*
 * @created 25/03/2026 ~~ 19:01
 * author: batu
 */

@Getter
@Setter
public abstract class TriAuthAbstractPageableVersionedModel<I> extends TriAuthAbstractVersionedModel<I> {

    private String direction;

    private String fieldName;

}
