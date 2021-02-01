package com.ghostdovahkiin.LibrApi.user.services;

import com.ghostdovahkiin.LibrApi.user.User;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface ListPageUserService {

    Page<User> listPages(Integer pages, Integer size);
}
