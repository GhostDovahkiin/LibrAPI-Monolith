package com.ghostdovahkiin.LibrApi.user.services;

import com.ghostdovahkiin.LibrApi.user.User;

import java.util.List;

@FunctionalInterface
public interface ListUserService {
    List<User> findAll();
}
