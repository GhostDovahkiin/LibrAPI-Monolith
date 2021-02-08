package com.ghostdovahkiin.librapi.user.services;

import com.ghostdovahkiin.librapi.exceptions.UserNotFoundException;
import com.ghostdovahkiin.librapi.user.User;
import com.ghostdovahkiin.librapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetUserServiceImpl implements GetUserService {
    private final UserRepository userRepository;


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
