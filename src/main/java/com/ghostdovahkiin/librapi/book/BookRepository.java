package com.ghostdovahkiin.librapi.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByCategoryName(String categoryName);
    boolean existsByIsbn(String isbn);
}
