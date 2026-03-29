package com.btoy.trial.persistence.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

import static com.btoy.trial.constants.Persistence.COL_IS_ACTIVE;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity<I> implements DeActivable {

    public static final String CREATED_BY = "CREATED_BY";
    public static final String CREATED_AT = "CREATED_AT";
    public static final String MODIFIED_BY = "MODIFIED_BY";
    public static final String MODIFIED_AT = "MODIFIED_AT";
    public static final String COL_ID = "ID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private I id;

    @CreatedDate
    @Column(name = CREATED_AT)
    private String createdAt;

    @CreatedBy
    @Column(name = CREATED_BY)
    private String createdBy;

    @LastModifiedDate
    @Column(name = MODIFIED_AT)
    private LocalDate modifiedAt;

    @LastModifiedBy
    @Column(name = MODIFIED_BY)
    private String modifiedBy;

    @Column(name = COL_IS_ACTIVE)
    private boolean isActive;

    @PrePersist
    private void preSetIsActive() {
        this.isActive = true;
    }

    @Override
    public void setIsActive(boolean active) {
        this.isActive = active;
    }

    @Override
    public boolean active() {
        return isActive;
    }
}
