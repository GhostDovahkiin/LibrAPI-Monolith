package com.ghostdovahkiin.librapi.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.domain.Page;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

  @NotNull(message = "Name cannot be null.")
  @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
  private String name;

  @NotNull(message = "Age cannot be null.")
  @Min(value = 13, message = "Your age must be higher or equal to 13 to use our services.")
  @Size(min = 2, max = 2, message = "Age must be between 2 and 2 characters.")
  private int age;

  @NotNull
  @Size(min = 8, max = 15, message = "Number must be between 8 and 15 characters.")
  private String phone;

  @NotNull(message = "Email cannot be null.")
  @Email(message = "This email is not valid, please enter a valid email.")
  private String email;

  @NotNull(message = "Sex cannot be null, the values are MASCULINO, FEMININO or INDEFINIDO")
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
