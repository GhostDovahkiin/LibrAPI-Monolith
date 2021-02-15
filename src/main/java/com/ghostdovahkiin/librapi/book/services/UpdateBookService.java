package com.ghostdovahkiin.librapi.book.services;

import com.ghostdovahkiin.librapi.book.BookDTO;

@FunctionalInterface
public interface UpdateBookService {
    void update(BookDTO book, Long id);
}
