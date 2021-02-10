package com.ghostdovahkiin.librapi.book;

import com.ghostdovahkiin.librapi.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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

    private String isbn;

    private String title;

    private String synopsis;

    private String author;

    @Column(name = "publication_year")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate publicationYear;

    @Column(name = "sell_price")
    private double sellPrice;

    @Column(name = "available_quantity")
    private int availableQuantity;

    @OneToMany
    @PrimaryKeyJoinColumn
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
