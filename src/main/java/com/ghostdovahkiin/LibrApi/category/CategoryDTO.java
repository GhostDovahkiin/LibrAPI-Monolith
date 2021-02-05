package com.ghostdovahkiin.LibrApi.category;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class CategoryDTO implements Serializable {

    /**
     *
     */
    private static final long SerialVersionUID = 675672435324534545L;

    private long id;

    @NotNull(message = "Category name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
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
