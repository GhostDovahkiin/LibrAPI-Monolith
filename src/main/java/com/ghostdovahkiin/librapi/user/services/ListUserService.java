package com.ghostdovahkiin.librapi.user.services;

import com.ghostdovahkiin.librapi.user.User;

import java.util.List;

@FunctionalInterface
public interface ListUserService {
    List<User> findAll();
}
