package com.btoy.trial.model.security;

/*
 * @created 25/03/2026 ~~ 19:00
 * author: batu
 */

import com.btoy.trial.model.base.TriAuthAbstractPageableModel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorityModel extends TriAuthAbstractPageableModel<Long> {

    private String name;

    private String code;
}
