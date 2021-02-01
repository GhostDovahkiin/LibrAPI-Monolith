package com.ghostdovahkiin.LibrApi.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.sun.istack.NotNull;
import org.springframework.data.domain.Page;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class UserDTO implements Serializable{
  private static final long serialVersionUID = 145485989485039832L;

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

  public static UserDTO from(User entity) {
    return UserDTO
            .builder()
            .id(entity.getId())
            .name(entity.getName())
            .age(entity.getAge())
            .phone(entity.getPhone())
            .email(entity.getEmail())
            .sex(entity.getSex())
            .build();
  }

  public static List<UserDTO> fromAll(List<User> user) {
    return user.stream().map(UserDTO::from).collect(Collectors.toList());
  }

  public static Page<UserDTO> fromPage(Page<User> pages) {
    return pages.map(UserDTO::from);
  }
}
