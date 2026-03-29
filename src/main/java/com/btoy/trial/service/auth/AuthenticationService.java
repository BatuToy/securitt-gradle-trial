package com.btoy.trial.service.auth;

import com.btoy.trial.model.security.UserPageableModel;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AuthenticationService {

    void logIn(UserPageableModel authenticationModel);

    UserPageableModel register(UserPageableModel authenticationModel);

    void logout();

}
