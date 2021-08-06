package com.fsd.service.impl;

import com.fsd.model.SecurityContext;
import com.fsd.model.User;
import com.fsd.repository.LoginRepository;
import com.fsd.service.LoginService;

public class LoginServiceImpl implements LoginService {
    public SecurityContext login(String userId, String password) {
        return new LoginRepository().login(userId, password);
    }

    public boolean signUp(User user) {
        return new LoginRepository().register(user);
    }
}
