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
import java.util.ArrayList;
import java.util.List;
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
    private static final long SerialVersionUID = 67563987234545L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long bookId;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bId", referencedColumnName = "book_id")
    private List<Category> category;

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
