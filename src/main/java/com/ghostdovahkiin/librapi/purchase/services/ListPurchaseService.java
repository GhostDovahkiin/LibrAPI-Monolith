package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.purchase.Purchase;

import java.util.List;

@FunctionalInterface
public interface ListPurchaseService {
    List<Purchase> findAll();
}
