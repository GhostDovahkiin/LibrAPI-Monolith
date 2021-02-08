package com.ghostdovahkiin.librapi.user.services;

import com.ghostdovahkiin.librapi.user.UserDTO;

@FunctionalInterface
public interface UpdateUserService {
    void update(UserDTO user, Long id);
}
