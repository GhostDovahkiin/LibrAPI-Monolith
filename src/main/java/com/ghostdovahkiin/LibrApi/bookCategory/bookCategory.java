package com.ghostdovahkiin.LibrApi.bookCategory;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(builderClassName = "Builder")
public class bookCategory {

    /**
     *
     */
    private static final long SerialVersionUID = 675672435324534545L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String category;

    public static bookCategory to(bookCategoryDTO dto) {
        return bookCategory
                .builder()
                .category(dto.getCategory())
                .build();
    }
}
