package com.btoy.trial.model.security;

/*
 * @created 25/03/2026 ~~ 19:00
 * author: batu
 */

import com.btoy.trial.model.base.TriAuthAbstractModel;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleModel extends TriAuthAbstractModel<Long> {

    private String name;

    private String code;

    private List<AuthorityModel> authorities;
}
