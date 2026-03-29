package com.btoy.trial.model.security;

/*
 * @created 25/03/2026 ~~ 17:48
 * author: batu
 */

import com.btoy.trial.model.base.TriAuthAbstractPageableModel;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

import static jakarta.validation.constraints.Pattern.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserPageableModel extends TriAuthAbstractPageableModel<Long> {

    @NotEmpty
    @NotNull
    private List<RoleModel> roles;

    @Min(value = 3)
    @Max(value = 50)
    private String userName;

    @Email(flags = {Flag.CASE_INSENSITIVE})
    private String email;

    @Min(value = 3)
    @Max(value = 25)
    private String name;

    @Min(value = 3)
    @Max(value = 25)
    private String sirName;

    private String password;

    @Pattern(regexp = "")
    private String identificationNumber;

}
