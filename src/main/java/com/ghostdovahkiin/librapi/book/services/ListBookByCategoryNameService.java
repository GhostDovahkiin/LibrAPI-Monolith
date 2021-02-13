
package com.ghostdovahkiin.librapi.book.services;

import com.ghostdovahkiin.librapi.book.Book;

import java.util.List;

@FunctionalInterface
public interface ListBookByCategoryNameService {
    List<Book> findBookByCategoriesName(String categoryName);
}