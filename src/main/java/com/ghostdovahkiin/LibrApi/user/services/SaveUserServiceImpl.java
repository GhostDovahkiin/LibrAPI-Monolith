package com.ghostdovahkiin.LibrApi.user.services;

import com.ghostdovahkiin.LibrApi.user.User;
import com.ghostdovahkiin.LibrApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveUserServiceImpl implements SaveUserService{
    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
