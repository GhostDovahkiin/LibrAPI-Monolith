package com.ghostdovahkiin.librapi.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException() { super("The book with specified ID doesn't exist.");}
}
