package com.ghostdovahkiin.librapi.book.services;

import com.ghostdovahkiin.librapi.book.Book;
import com.ghostdovahkiin.librapi.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListBookByCategoryNameServiceImpl implements ListBookByCategoryNameService{
    private final BookRepository bookRepository;

    @Override
    public List<Book> findBookByCategoriesName(String categoryName) {
        return bookRepository.findBookByCategoryName(categoryName);
    }
}