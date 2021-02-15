package com.ghostdovahkiin.librapi.book.services;

import com.ghostdovahkiin.librapi.book.Book;
import com.ghostdovahkiin.librapi.book.BookRepository;
import com.ghostdovahkiin.librapi.category.Category;
import com.ghostdovahkiin.librapi.exceptions.BookAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SaveBookServiceImpl implements SaveBookService{
    private final BookRepository bookRepository;

    @Override
    public void save(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())){
            throw new BookAlreadyExistsException();
        }
        if(!book.getCategory().isEmpty()) {
            List<Category> categories = new ArrayList<>();
            for (Category c: book.getCategory()){
                c.getCategoryId();
                categories.add(c);
            }
            book.setCategory(categories);
        }
        bookRepository.save(book);
    }
}
