package com.ghostdovahkiin.librapi.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findPurchaseByStatus(Status status);
}
