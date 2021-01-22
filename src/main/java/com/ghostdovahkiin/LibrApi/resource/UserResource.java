package com.ghostdovahkiin.LibrApi.resource;

import com.ghostdovahkiin.LibrApi.model.User;
import com.ghostdovahkiin.LibrApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Object> list() {
        List<User> usersFound = userRepository.findAll();
        return !usersFound.isEmpty() ? ResponseEntity.ok(usersFound) : ResponseEntity.noContent().build();
    }
}
