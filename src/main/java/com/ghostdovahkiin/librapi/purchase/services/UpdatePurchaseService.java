package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.purchase.PurchaseDTO;

@FunctionalInterface
public interface UpdatePurchaseService {
    void update(PurchaseDTO purchase, Long id);
}
