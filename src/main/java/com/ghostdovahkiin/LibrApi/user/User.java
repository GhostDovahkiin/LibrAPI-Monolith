package com.ghostdovahkiin.LibrApi.user;

import lombok.*;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(builderClassName = "Builder")
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    private String phone;

    private String email;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    public static User to(UserDTO dto) {
        return User
                .builder()
                .name(dto.getName())
                .age(dto.getAge())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .sex(dto.getSex())
                .build();
    }
}
