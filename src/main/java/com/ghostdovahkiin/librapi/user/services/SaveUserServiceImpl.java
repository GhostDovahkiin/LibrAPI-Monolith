package com.ghostdovahkiin.librapi.user.services;

import com.ghostdovahkiin.librapi.exceptions.UserAlreadyExistsException;
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
        if(userRepository.existsByEmail(user.getEmail()) || userRepository.existsByPhone(user.getPhone())){
            throw new UserAlreadyExistsException();
        }
        userRepository.save(user);
    }
}
