package com.btoy.trial.service.auth;

import com.btoy.trial.model.security.UserPageableModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public void logIn(UserPageableModel authenticationModel) {
    }

    @Override
    public void logout() {

    }

    @Override
    public UserPageableModel register(UserPageableModel authenticationModel) {
        return null;
    }
}
