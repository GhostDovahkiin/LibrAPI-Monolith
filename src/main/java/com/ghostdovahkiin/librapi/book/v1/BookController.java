package com.ghostdovahkiin.librapi.book.v1;

import com.ghostdovahkiin.librapi.book.Book;
import com.ghostdovahkiin.librapi.book.BookDTO;
import com.ghostdovahkiin.librapi.book.services.GetBookService;
import com.ghostdovahkiin.librapi.book.services.ListBookByCategoryNameService;
import com.ghostdovahkiin.librapi.book.services.ListBookService;
import com.ghostdovahkiin.librapi.book.services.SaveBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/api/books")
public class BookController {
    private final ListBookByCategoryNameService listBookByCategoryNameService;
    private final ListBookService listBookService;
    private final GetBookService getBookService;
    private final SaveBookService saveBookService;

    @GetMapping(path = "/{categoryName}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> findBookByCategoryName(@PathVariable String categoryName){
        return BookDTO.fromAll(listBookByCategoryNameService.findBookByCategoryName(categoryName));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> findAll() {
        return BookDTO.fromAll(listBookService.findAll());
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO findById(@PathVariable Long id) {
        return BookDTO.from(getBookService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody BookDTO bookDTO){
        saveBookService.save(Book.to(bookDTO));
    }
}