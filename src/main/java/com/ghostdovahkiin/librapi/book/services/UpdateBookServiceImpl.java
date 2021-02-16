package com.ghostdovahkiin.librapi.book.services;

import com.ghostdovahkiin.librapi.book.Book;
import com.ghostdovahkiin.librapi.book.BookRepository;
import com.ghostdovahkiin.librapi.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateBookServiceImpl implements UpdateBookService{
    private final BookRepository bookRepository;

    @Override
    public void update(Book book, Long id) {
        Book bookFound = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookFound.setIsbn(book.getIsbn());
        bookFound.setAuthor(book.getAuthor());
        bookFound.setAvailableQuantity(book.getAvailableQuantity());
        bookFound.setCategory(book.getCategory());
        bookFound.setPublicationYear(book.getPublicationYear());
        bookFound.setSellPrice(book.getSellPrice());
        bookFound.setSynopsis(book.getSynopsis());
        bookFound.setTitle(book.getTitle());
        bookRepository.save(bookFound);
    }
}
