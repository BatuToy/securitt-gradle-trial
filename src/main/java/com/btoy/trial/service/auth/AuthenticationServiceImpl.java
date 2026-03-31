package com.btoy.trial.service.auth;

import com.btoy.trial.model.security.UserModel;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public void logIn(UserModel authenticationModel) {
    }

    @Override
    public void logout() {

    }

    @Override
    public UserModel register(UserModel authenticationModel) {
        return null;
    }
}
