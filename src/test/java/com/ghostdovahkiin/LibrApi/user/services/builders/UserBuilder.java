package com.ghostdovahkiin.LibrApi.user.services.builders;

import com.ghostdovahkiin.LibrApi.user.Sex;
import com.ghostdovahkiin.LibrApi.user.User;

public class UserBuilder {
    public static User.Builder createUser() {
        return User.builder()
                .name("Pedro")
                .age(22)
                .phone("+5583986862912")
                .email("pedro.sousa@dcx.ufpb.br")
                .sex(Sex.MASCULINO);
    }
}
