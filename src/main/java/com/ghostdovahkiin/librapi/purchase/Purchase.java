package com.ghostdovahkiin.librapi.purchase;

import com.ghostdovahkiin.librapi.book.Book;
import com.ghostdovahkiin.librapi.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "purchase")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class Purchase implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 98263987234545L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.DETACH)
    @PrimaryKeyJoinColumn(name = "book_id")
    private Set<Book> purchasedBooks;

    @Column(name = "amount_to_pay")
    private double amountToPay;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public static Purchase to(PurchaseDTO dto) {
        return Purchase
                .builder()
                .user(dto.getUser())
                .purchasedBooks(dto.getPurchasedBooks())
                .amountToPay(dto.getAmountToPay())
                .status(dto.getStatus())
                .build();
    }
}
