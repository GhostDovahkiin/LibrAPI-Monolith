package com.ghostdovahkiin.LibrApi.user.services;

import com.ghostdovahkiin.LibrApi.user.User;

@FunctionalInterface
public interface UpdateUserService {
    void update(User user, Long id);
}
