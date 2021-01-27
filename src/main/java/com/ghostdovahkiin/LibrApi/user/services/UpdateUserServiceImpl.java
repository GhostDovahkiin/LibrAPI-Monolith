package com.ghostdovahkiin.LibrApi.user.services;

import com.ghostdovahkiin.LibrApi.exceptions.UserNotFoundException;
import com.ghostdovahkiin.LibrApi.user.User;
import com.ghostdovahkiin.LibrApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateUserServiceImpl implements UpdateUserService{
    private final UserRepository userRepository;

    @Override
    public void update(User user, Long id) {
        User userfound = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userfound.setName(user.getName());
        userfound.setAge(user.getAge());
        userfound.setPhone(user.getPhone());
        userRepository.save(userfound);
    }
}
