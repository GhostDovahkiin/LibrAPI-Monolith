package com.ghostdovahkiin.librapi.user.builders;

import com.ghostdovahkiin.librapi.user.Sex;
import com.ghostdovahkiin.librapi.user.User;

public class UserBuilder {
    public static User.Builder createUser() {
        return User.builder()
                .id(145485989485039832L)
                .name("Pedro")
                .age(22)
                .phone("+5583986862912")
                .email("pedro.sousa@dcx.ufpb.br")
                .sex(Sex.MASCULINO);
    }
}
