package com.ghostdovahkiin.librapi.purchase.builder;

import com.ghostdovahkiin.librapi.book.Book;
import com.ghostdovahkiin.librapi.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchase.Status;
import com.ghostdovahkiin.librapi.user.User;

import java.util.HashSet;
import java.util.Set;

import static com.ghostdovahkiin.librapi.book.builder.BookBuilder.createBook;
import static com.ghostdovahkiin.librapi.user.builders.UserBuilder.createUser;

public class PurchaseBuilder {

    public static Purchase.Builder createPurchase() {
        User user = createUser().build();
        Set<Book> books = new HashSet<>();
        books.add(createBook().title("book1").isbn("978-3-16-148410-0").build());
        books.add(createBook().title("book2").isbn("978-3-16-148410-1").build());
        return Purchase.builder()
                .id(123L)
                .user(user)
                .purchasedBooks(books)
                .amountToPay(229.90)
                .status(Status.COMPLETED);
    }
}
