package com.btoy.trial.service.user;

import com.btoy.trial.model.security.UserModel;

/*
 * @created 25/03/2026 ~~ 17:47
 * author: batu
 */
public interface UserService {

    void save();

    UserModel getById();

    void update();
}
