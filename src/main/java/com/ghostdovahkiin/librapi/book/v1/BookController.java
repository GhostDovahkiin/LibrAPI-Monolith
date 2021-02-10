package com.ghostdovahkiin.librapi.book.v1;

import com.ghostdovahkiin.librapi.book.BookDTO;
import com.ghostdovahkiin.librapi.book.services.ListBookByCategoryNameService;
import com.ghostdovahkiin.librapi.book.services.ListBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/api/books")
public class BookController {
    private final ListBookByCategoryNameService listBookByCategoryNameService;
    private final ListBookService listBookService;

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
}
