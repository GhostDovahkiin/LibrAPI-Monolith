package com.ghostdovahkiin.LibrApi.user;

import com.sun.istack.NotNull;
import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int age;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sex sex;
}
