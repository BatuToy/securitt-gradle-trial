package com.btoy.trial.persistence.base;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractVersionedEntity<I> extends AbstractEntity<I> {

    @Version
    private Integer version;

}
