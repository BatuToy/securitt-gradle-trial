package com.btoy.trial.persistence.entity.authority;

import com.btoy.trial.persistence.base.AbstractVersionedEntity;
import com.btoy.trial.persistence.entity.role.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import static com.btoy.trial.constants.ApplicationConfigurationConstants.APP_SCHEMA;
import static com.btoy.trial.constants.ApplicationConstants.UNDERSCORE;
import static com.btoy.trial.constants.Persistence.*;

@Getter
@Setter
//@SQLDelete(sql = "UPDATE tri.tri_authority AS t SET t.is_active=false WHERE t.id=?1")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = Authority.AUTHORITY_ENTITY)
@Table(name = Authority.AUTHORITY_TABLE,
        schema = APP_SCHEMA,
        uniqueConstraints = {
                @UniqueConstraint(name = Authority.UK_AUTHORITY_CODE, columnNames = Authority.COL_CODE)
        }
)
public class Authority extends AbstractVersionedEntity<Long> {

    public static final String AUTHORITY_ENTITY = "AUTHORITY";
    public static final String AUTHORITY_TABLE = APP_SCHEMA + UNDERSCORE + AUTHORITY_ENTITY;

    private static final String COL_ROLE = "ROLE_ID";
    private static final String COLE_NAME = "NAME";
    public static final String COL_CODE = "CODE";

    public static final String FK_AUTHORITY_ROLE = FOREIGN_KEY_PREFIX + AUTHORITY_TABLE + UNDERSCORE + COL_ROLE;
    public static final String UK_AUTHORITY_CODE = UNIQUE_PREFIX + AUTHORITY_ENTITY + UNDERSCORE + COL_CODE;

    @Column(name = COL_CODE)
    private String code;
    @Column(name = COLE_NAME)
    private String name;

    @ManyToOne(
            cascade = CascadeType.ALL
            //fetch = FetchType.EAGER
    )
    @JoinColumn(name = COL_ROLE,
            referencedColumnName = COL_ID,
            foreignKey = @ForeignKey(
                    name = FK_AUTHORITY_ROLE
            )
    )
    @Fetch(FetchMode.SELECT)
    private Role role;

}
