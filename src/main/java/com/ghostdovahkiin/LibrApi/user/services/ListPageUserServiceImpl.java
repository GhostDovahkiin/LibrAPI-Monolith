package com.ghostdovahkiin.LibrApi.user.services;

import com.ghostdovahkiin.LibrApi.user.User;
import com.ghostdovahkiin.LibrApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListPageUserServiceImpl implements ListPageUserService{

    private final UserRepository userRepository;

    @Override
    public Page<User> listPages(Integer pages, Integer size) {
        Pageable pageRequest = PageRequest.of(pages, size, Sort.Direction.ASC, "id");
        return userRepository.findAll(pageRequest);
    }
}
