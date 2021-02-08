package com.ghostdovahkiin.librapi.user.services;

import com.ghostdovahkiin.librapi.user.User;
import com.ghostdovahkiin.librapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveUserServiceImpl implements SaveUserService{
    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
