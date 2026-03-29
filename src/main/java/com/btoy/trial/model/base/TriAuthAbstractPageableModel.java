package com.btoy.trial.model.base;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/*
 * @created 25/03/2026 ~~ 19:01
 * author: batu
 */

@Getter
@Setter
@MappedSuperclass
public class TriAuthAbstractPageableModel<I> extends TriAuthAbstractModel<I>  {

    private String direction;

    private String fieldName;

}
