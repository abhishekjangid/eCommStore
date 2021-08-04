package com.fsd.service.impl;

import com.fsd.model.User;
import com.fsd.repository.LoginRepository;
import com.fsd.service.LoginService;

public class LoginServiceImpl implements LoginService {
    public boolean login(String userId, String password) {
        return new LoginRepository().login(userId, password);
    }

    public boolean signUp(User user) {
        return new LoginRepository().register(user);
    }
}
