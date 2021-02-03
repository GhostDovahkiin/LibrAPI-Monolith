package com.ghostdovahkiin.LibrApi.user.services;

import com.ghostdovahkiin.LibrApi.user.User;

@FunctionalInterface
public interface GetUserService {
    User findById(Long id);
}
