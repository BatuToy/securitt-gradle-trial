package com.btoy.trial.persistence.base;

/*
 * @created 28/03/2026 ~~ 17:26
 * author: batu
 */
public interface DeActivable {

    default void deActivate() {
        setIsActive(false);
    }

    void setIsActive(boolean active);

    boolean active();
}
