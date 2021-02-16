package com.ghostdovahkiin.librapi.category;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(builderClassName = "Builder")
public class Category implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 675672435324534545L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;

    private String name;

    public Category(String test) {
        this.name = test;
    }

    public static Category to(CategoryDTO dto) {
        return Category
                .builder()
                .name(dto.getName())
                .build();
    }
}
