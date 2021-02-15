package com.ghostdovahkiin.librapi.book;

import com.ghostdovahkiin.librapi.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
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
    private static final long serialVersionUID = 67563987234545L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long bookId;

    @NotNull(message = "ISBN cannot be null")
    @Size(min = 17, max = 17, message = "ISBN must have 17 characters like: 978-3-16-148410-0")
    private String isbn;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Synopsis cannot be null")
    private String synopsis;

    @NotNull(message = "Author cannot be null")
    private String author;

    @Column(name = "publication_year")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Publication Year cannot be null")
    private LocalDate publicationYear;

    @Column(name = "sell_price")
    @NotNull(message = "Sell price cannot be null, the minimum value is zero")
    @Min(0)
    private double sellPrice;

    @Column(name = "available_quantity")
    @Min(0)
    @NotNull(message = "Quantity cannot be null, the minimum value is zero")
    private int availableQuantity;

    @ManyToMany(cascade = CascadeType.DETACH)
    @PrimaryKeyJoinColumn
    @NotNull(message = "A boot must have a category")
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
