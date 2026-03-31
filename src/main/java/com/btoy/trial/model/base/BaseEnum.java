package com.btoy.trial.model.base;

/*
 * @created 29/03/2026 ~~ 11:48
 * author: batu
 */
public interface BaseEnum<T> {

    Integer getDbValue();

    String getLabel();

    T getValue();
}
