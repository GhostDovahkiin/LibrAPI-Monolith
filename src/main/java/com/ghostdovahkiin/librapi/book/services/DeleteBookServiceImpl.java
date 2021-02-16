package com.ghostdovahkiin.librapi.book.services;

import com.ghostdovahkiin.librapi.book.BookRepository;
import com.ghostdovahkiin.librapi.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteBookServiceImpl implements DeleteBookService{
    private final BookRepository bookRepository;

    @Override
    public void delete(Long id) {
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException();
        }
        bookRepository.deleteById(id);
    }
}
