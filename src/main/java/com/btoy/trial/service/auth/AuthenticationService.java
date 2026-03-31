package com.btoy.trial.service.auth;

import com.btoy.trial.model.security.UserModel;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AuthenticationService {

    void logIn(@Valid UserModel authenticationModel);

    UserModel register(@Valid UserModel authenticationModel);

    void logout();

}
