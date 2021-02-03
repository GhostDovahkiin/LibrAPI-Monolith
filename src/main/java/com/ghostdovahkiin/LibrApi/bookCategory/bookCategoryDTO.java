package com.ghostdovahkiin.LibrApi.bookCategory;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class bookCategoryDTO implements Serializable {

    /**
     *
     */
    private static final long SerialVersionUID = 675672435324534545L;

    private long id;

    @NotNull
    private String category;
}
