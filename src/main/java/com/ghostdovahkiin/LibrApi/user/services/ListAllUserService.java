package com.ghostdovahkiin.LibrApi.user.services;

import com.ghostdovahkiin.LibrApi.user.User;

import java.util.List;

@FunctionalInterface
public interface ListAllUserService {
    List<User> findAll();
}
