package com.fsd.service;

import com.fsd.model.SecurityContext;

public interface LoginService {
    SecurityContext login(String userId, String password);
}
