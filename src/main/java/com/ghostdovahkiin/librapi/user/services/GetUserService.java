package com.ghostdovahkiin.librapi.user.services;

import com.ghostdovahkiin.librapi.user.User;

@FunctionalInterface
public interface GetUserService {
    User findById(Long id);
}
