package com.ghostdovahkiin.librapi.user.services;

import com.ghostdovahkiin.librapi.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPageUserService {

    Page<User> listPages(Pageable pageable);
}
