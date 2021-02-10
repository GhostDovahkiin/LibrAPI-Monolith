package com.ghostdovahkiin.librapi.book.services;

import com.ghostdovahkiin.librapi.book.Book;

@FunctionalInterface
public interface GetBookService {
    Book findById(Long id);
}
