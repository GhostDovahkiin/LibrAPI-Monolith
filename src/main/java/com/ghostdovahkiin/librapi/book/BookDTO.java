package com.ghostdovahkiin.librapi.book;

import com.ghostdovahkiin.librapi.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class BookDTO implements Serializable {
    /**
     *
     */
    private static final long SerialVersionUID = 675638275324534545L;
    private long id;

    @Size(min = 17, max = 17, message = "ISBN must contain 17 characters" + "\n Ex.: 978-3-16-148410-0")
    @NotNull(message = "ISBN cannot be null")
    private long isnb;

    @Size(min = 1, max = 50, message = "Title must be between 1 and 50 characters")
    @NotNull(message = "Book Title cannot be null")
    private String title;

    @Size(min = 1, max = 120, message = "Synopsis must be between 1 and 120 characters")
    @NotNull(message = "Synopsis cannot be null")
    private String synopsis;

    @NotNull(message = "Author cannot be null")
    private String author;

    @NotNull(message = "Publication Year cannot be null")
    @Max(2021)
    @Min(1)
    private int publicationYear;

    @NotNull(message = "Sell Price cannot be null")
    @Min(0)
    private double sellPrice;

    @Min(0)
    @NotNull(message = "Available quantity cannot be null")
    private int availableQuantity;

    private List<Category> category;

    public static BookDTO from(Book entity) {
        return BookDTO
                .builder()
                .id(entity.getId())
                .isnb(entity.getIsbn())
                .title(entity.getTitle())
                .synopsis(entity.getSynopsis())
                .author(entity.getAuthor())
                .publicationYear(entity.getPublicationYear())
                .sellPrice(entity.getSellPrice())
                .availableQuantity(entity.getAvailableQuantity())
                .category(entity.getCategory())
                .build();
    }

    public static List<BookDTO> fromAll(List<Book> books) {
        return books.stream().map(BookDTO::from).collect(Collectors.toList());
    }

}
