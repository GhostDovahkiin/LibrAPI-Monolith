package com.ghostdovahkiin.librapi.book.services;

import com.ghostdovahkiin.librapi.book.Book;
import com.ghostdovahkiin.librapi.book.BookRepository;
import com.ghostdovahkiin.librapi.exceptions.BookAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveBookServiceImpl implements SaveBookService{
    private final BookRepository bookRepository;

    @Override
    public void save(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())){
            throw new BookAlreadyExistsException();
        }
        bookRepository.save(book);
    }
}
