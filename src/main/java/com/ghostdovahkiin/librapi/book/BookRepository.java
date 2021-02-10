package com.ghostdovahkiin.librapi.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByCategoryName(String categoryName);
    boolean existsByIsbn(String isbn);
}
