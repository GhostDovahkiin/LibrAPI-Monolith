package com.ghostdovahkiin.LibrApi.user.v1;

import com.ghostdovahkiin.LibrApi.user.User;
import com.ghostdovahkiin.LibrApi.user.UserDTO;

import com.ghostdovahkiin.LibrApi.user.services.ListAllUserService;
import com.ghostdovahkiin.LibrApi.user.services.ListOneUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/users")
public class UserController {

    private final ListAllUserService listAllUserService;
    private final ListOneUserService listOneUserService;

    @GetMapping
    public List<UserDTO> findAll() {
        return UserDTO.fromAll(listAllUserService.findAll());
    }

    @GetMapping(value = "/{id}")
    public UserDTO findByID(@PathVariable Long id) {
        return UserDTO.from(listOneUserService.findById(id));
    }
}
