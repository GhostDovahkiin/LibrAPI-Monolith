package com.ghostdovahkiin.librapi.exceptions;

public class PurchaseNotFoundException extends RuntimeException{
    public PurchaseNotFoundException() { super("The purchase with specified ID doesn't exist."); }
}
