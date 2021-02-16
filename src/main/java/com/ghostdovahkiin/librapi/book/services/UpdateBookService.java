package com.ghostdovahkiin.librapi.book.services;

import com.ghostdovahkiin.librapi.book.Book;

@FunctionalInterface
public interface UpdateBookService {
    void update(Book book, Long id);
}
