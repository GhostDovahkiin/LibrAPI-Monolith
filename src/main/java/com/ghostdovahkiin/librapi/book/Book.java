package com.ghostdovahkiin.librapi.book;

import com.ghostdovahkiin.librapi.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(builderClassName = "Builder")
public class Book implements Serializable {
    /**
     *
     */
    private static final long SerialVersionUID = 67563987234545L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long isbn;

    private String title;

    private String synopsis;

    private String author;

    private int publicationYear;

    private double sellPrice;

    private int availableQuantity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<Category> category;

    public static Book to(BookDTO dto) {
        return Book
                .builder()
                .title(dto.getTitle())
                .synopsis(dto.getSynopsis())
                .author(dto.getAuthor())
                .publicationYear(dto.getPublicationYear())
                .sellPrice(dto.getSellPrice())
                .availableQuantity(dto.getAvailableQuantity())
                .category(dto.getCategory())
                .build();
    }
}
