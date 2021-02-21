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
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class Book implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 67563987234545L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String isbn;

    private String title;

    private String synopsis;

    private String author;

    private LocalDate publicationYear;

    private double sellPrice;

    private int availableQuantity;

    @ManyToMany(cascade = CascadeType.DETACH)
    @PrimaryKeyJoinColumn
    private Set<Category> category = new HashSet<>();

    public static Book to(BookDTO dto) {
        return Book
                .builder()
                .isbn(dto.getIsbn())
                .title(dto.getTitle())
                .synopsis(dto.getSynopsis())
                .author(dto.getAuthor())
                .publicationYear(dto.getPublicationYear())
                .sellPrice(dto.getSellPrice())
                .category(dto.getCategory())
                .availableQuantity(dto.getAvailableQuantity())
                .build();
    }
}
