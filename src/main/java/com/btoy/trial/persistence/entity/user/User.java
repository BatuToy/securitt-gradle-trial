package com.btoy.trial.persistence.entity.user;

import com.btoy.trial.persistence.base.AbstractVersionedEntity;
import com.btoy.trial.persistence.entity.role.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.btoy.trial.constants.ApplicationConfigurationConstants.APP_SCHEMA;
import static com.btoy.trial.constants.ApplicationConstants.*;
import static com.btoy.trial.constants.Persistence.*;
import static com.btoy.trial.persistence.entity.user.User.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = ENTITY_USER)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = UNIQUE_EMAIL, columnNames = COL_EMAIL),
        @UniqueConstraint(name = UNIQUE_USER_ID_NUMBER, columnNames = COL_USER_IDENTIFICATION_NUMBER),
        @UniqueConstraint(name = UNIQUE_USER_ID_NUMBER, columnNames = COL_USER_IDENTIFICATION_NUMBER)
},
        name = TABLE_USER,
        comment = TABLE_COMMENT,
        schema = APP_SCHEMA
)
public class User extends AbstractVersionedEntity<Long> {
    // Cols
    public static final String COL_JOIN_USER = "USER_ID";
    public static final String COL_JOIN_ROLE = "ROLE_ID";
    public static final String COL_USER_IDENTIFICATION_NUMBER = "IDENTIFICATION_NUMBER";
    public static final String COL_EMAIL = "EMAIL";

    private static final String COL_USER_NAME = "USER_NAME";
    private static final String COL_NAME = "NAME";
    private static final String COL_SIR_NAME = "SIR_NAME";
    private static final String COL_PHONE_NUMBER = "PHONE_NUMBER";
    private static final String COL_PASSWORD = "PASSWORD";

    // Table
    public static final String TABLE_USER = "TRI_USER";
    public static final String TABLE_USER_ROLE = "TRI_USER_ROLE";
    public static final String ENTITY_USER = "USER";
    public static final String TABLE_COMMENT = "Table for holding users data.";

    // Constraints
    public static final String UNIQUE_EMAIL = UNIQUE_PREFIX + TABLE_USER + UNDERSCORE + COL_EMAIL;
    public static final String UNIQUE_USER_ID_NUMBER = UNIQUE_PREFIX + TABLE_USER + UNDERSCORE + COL_USER_IDENTIFICATION_NUMBER;
    public static final String UNIQUE_USER_NAME = UNIQUE_PREFIX + TABLE_USER + UNDERSCORE + COL_USER_NAME;

    // tri_user_role fk constraints
    public static final String FK_USER_ROLE_USER_ID = FOREIGN_KEY_PREFIX + TABLE_USER_ROLE + UNDERSCORE + COL_JOIN_USER;
    public static final String FK_USER_ROLE_ROLE_ID = FOREIGN_KEY_PREFIX + TABLE_USER_ROLE + UNDERSCORE + COL_JOIN_ROLE;

    @Column(name = COL_USER_NAME)
    private String userName;

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_SIR_NAME)
    private String sirName;

    @Column(name = COL_USER_IDENTIFICATION_NUMBER)
    private String identificationNumber;

    @Column(name = COL_EMAIL)
    private String email;

    @Column(name = COL_PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = COL_PASSWORD)
    private String password;

    @ManyToMany
    @JoinTable(name = TABLE_USER_ROLE,
            foreignKey = @ForeignKey(name = FK_USER_ROLE_USER_ID),
            inverseForeignKey = @ForeignKey(name = FK_USER_ROLE_ROLE_ID),
            joinColumns = {
                    @JoinColumn(name = COL_JOIN_USER, referencedColumnName = COL_ID),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = COL_JOIN_ROLE, referencedColumnName = COL_ID),

            })
    private List<Role> roles;
}
