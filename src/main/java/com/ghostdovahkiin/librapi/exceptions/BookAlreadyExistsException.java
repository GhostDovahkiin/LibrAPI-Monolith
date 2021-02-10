package com.ghostdovahkiin.librapi.exceptions;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException() { super("The book with specified ISBN already exists");}
}
