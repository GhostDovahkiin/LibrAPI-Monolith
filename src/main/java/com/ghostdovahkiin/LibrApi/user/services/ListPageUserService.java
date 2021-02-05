package com.ghostdovahkiin.LibrApi.user.services;

import com.ghostdovahkiin.LibrApi.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPageUserService {

    Page<User> listPages(Pageable pageable);
}
