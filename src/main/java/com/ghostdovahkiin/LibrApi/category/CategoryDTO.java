package com.ghostdovahkiin.LibrApi.category;


import com.ghostdovahkiin.LibrApi.user.User;
import com.ghostdovahkiin.LibrApi.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class CategoryDTO implements Serializable {

    /**
     *
     */
    private static final long SerialVersionUID = 675672435324534545L;

    private long id;

    @NotNull
    private String name;

    public static CategoryDTO from(Category entity) {
        return CategoryDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static List<CategoryDTO> fromAll(List<Category> categories) {
        return categories.stream().map(CategoryDTO::from).collect(Collectors.toList());
    }
}
