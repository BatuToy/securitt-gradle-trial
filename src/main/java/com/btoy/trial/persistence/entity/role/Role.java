package com.btoy.trial.persistence.entity.role;

import com.btoy.trial.persistence.base.DeActivable;
import com.btoy.trial.persistence.entity.authority.Authority;
import com.btoy.trial.persistence.base.AbstractVersionedEntity;
import com.btoy.trial.persistence.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import static com.btoy.trial.constants.ApplicationConfigurationConstants.*;
import static com.btoy.trial.constants.ApplicationConstants.UNDERSCORE;
import static com.btoy.trial.constants.Persistence.*;
import static com.btoy.trial.persistence.entity.role.Role.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = Role.ENTITY_ROLE)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = UNIQUE_CODE, columnNames = COL_CODE)
        },
        name = Role.TABLE_ROLE,
        schema = APP_SCHEMA
)
public class Role extends AbstractVersionedEntity<Long>  {

    public static final String ENTITY_ROLE = "TRI_ROLE";
    public static final String TABLE_ROLE = "TRI_ROLE";

    private static final String COL_NAME = "NAME";
    public static final String COL_CODE = "CODE";

    public static final String UNIQUE_CODE = UNIQUE_PREFIX + TABLE_ROLE + UNDERSCORE + COL_CODE;

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_CODE)
    private String code;

    @OneToMany(mappedBy = "role",
            targetEntity = Authority.class,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private Set<Authority> authorities;

    @ManyToMany(mappedBy = "roles",
            targetEntity = User.class,
            fetch = FetchType.LAZY)
    private List<User> users;

}


