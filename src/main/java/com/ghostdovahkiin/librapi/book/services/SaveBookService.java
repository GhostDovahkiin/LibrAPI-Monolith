package com.ghostdovahkiin.librapi.book.services;

import com.ghostdovahkiin.librapi.book.Book;

@FunctionalInterface
public interface SaveBookService {
    void save(Book book);
}
