package com.btoy.trial.service.user;

import com.btoy.trial.model.security.UserModel;
import com.btoy.trial.persistence.dao.user.UserRepository;
import org.springframework.stereotype.Service;

/*
 * @created 01/04/2026 ~~ 19:56
 * author: batu
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void save(UserModel userModel) {

    }

    @Override
    public UserModel getById(Long id) {
        return null;
    }

    @Override
    public void update(UserModel userModel) {

    }
}
