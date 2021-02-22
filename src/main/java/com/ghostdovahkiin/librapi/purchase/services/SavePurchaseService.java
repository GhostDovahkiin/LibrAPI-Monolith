package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.purchase.Purchase;

@FunctionalInterface
public interface SavePurchaseService {
    void save(Purchase purchase);
}
